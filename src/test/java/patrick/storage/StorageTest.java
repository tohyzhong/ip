package patrick.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import patrick.exception.PatrickException;
import patrick.task.Deadline;
import patrick.task.TaskList;
import patrick.task.ToDo;

public class StorageTest {

    // Temporary directory and file so that we do not modify the real one
    @TempDir
    Path tempFilePath;

    @Test
    public void constructor_invalidFilepath_throwsPatrickException() {
        Assertions.assertThrowsExactly(PatrickException.class, () -> new Storage("./a\tsd"));
    }

    @Test
    public void constructor_validFilepathMissingDirectory_createsDirectoryAndFile() throws PatrickException {
        Path filePath = this.tempFilePath.resolve("testdir/testfile.txt");
        new Storage(filePath.toString());
        Assertions.assertAll(
                () -> Assertions.assertTrue(Files.exists(filePath), "File should exist."),
                () -> Assertions.assertTrue(Files.isDirectory(filePath.getParent()), "Directory should exist."));
    }

    @Test
    public void load_corruptTaskString_omitCorruptTasks() throws IOException, PatrickException {
        Path filePath = this.tempFilePath.resolve("corrupt/corrupt.txt");
        String str = "T | TRUE | Task 1\nASD | FALSE | Task 2 Corrupt\nD | FALSE | Task 3 | 2026-01-27";
        Files.createDirectory(filePath.getParent());
        Files.createFile(filePath);
        Files.writeString(filePath, str);

        Storage storage = new Storage(filePath.toString());
        TaskList tasks = storage.load();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, tasks.getSize(),
                        "Should skip corrupt task and load 2 valid tasks."),
                () -> Assertions.assertEquals("Task 1", tasks.getTask(0).getDescription()),
                () -> Assertions.assertEquals("Task 3", tasks.getTask(1).getDescription()));
    }

    @Test
    public void load_normalTasks_returnTaskList() throws IOException, PatrickException {
        Path filePath = this.tempFilePath.resolve("normal/normal_load.txt");
        String str = "T | 0 | Task 1\nD | 1 | Task 2 | 2023-12-12";
        Files.createDirectory(filePath.getParent());
        Files.createFile(filePath);
        Files.writeString(filePath, str);

        Storage storage = new Storage(filePath.toString());
        TaskList tasks = storage.load();

        Assertions.assertEquals(2, tasks.getSize(), "Should load exactly 2 tasks.");
        Assertions.assertEquals("Task 1", tasks.getTask(0).getDescription());
        Assertions.assertEquals("Task 2", tasks.getTask(1).getDescription());
    }

    @Test
    public void save_readOnlyFile_throwsPatrickException() throws IOException, PatrickException {
        Path filePath = this.tempFilePath.resolve("readonly/readonly.txt");
        Files.createDirectory(filePath.getParent());
        Files.createFile(filePath);
        Storage storage = new Storage(filePath.toString());
        filePath.toFile().setReadOnly();

        try {
            Assertions.assertThrows(PatrickException.class, () -> {
                storage.save(new TaskList());
            }, "Should throw PatrickException when file is read-only.");
        } finally {
            filePath.toFile().setWritable(true);
        }
    }

    @Test
    public void save_normalTasks_saveTasksToFile() throws IOException, PatrickException {
        Path filePath = this.tempFilePath.resolve("normal/normal_save.txt");
        Files.createDirectory(filePath.getParent());
        Files.createFile(filePath);

        Storage storage = new Storage(filePath.toString());
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task 1"));
        tasks.addTask(new Deadline("Task 2", "2023-12-12"));
        tasks.getTask(1).setDone();
        storage.save(tasks);

        List<String> actual = Files.readAllLines(filePath);
        List<String> expected = new ArrayList<>();
        expected.add("T | FALSE | Task 1");
        expected.add("D | TRUE | Task 2 | 2023-12-12");

        Assertions.assertEquals(actual.get(0), expected.get(0), "Task 1 saved.");
        Assertions.assertEquals(actual.get(1), expected.get(1), "Task 2 saved.");
    }
}
