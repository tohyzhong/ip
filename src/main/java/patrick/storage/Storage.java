package patrick.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import patrick.exceptions.PatrickException;
import patrick.parser.Parser;
import patrick.tasks.Task;
import patrick.tasks.TaskList;

public class Storage {
    private String filepath;

    public Storage(String filepath) throws PatrickException {
        this.filepath = filepath;
        try {
            Path path = Paths.get(filepath);
            // Extract the directory path
            if (path.getParent() != null) {
                // Create directories that do not exist, no error or new directory created if
                // already exists
                Files.createDirectories(path.getParent());
            }

            // Check if file exist, if not create file
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException err) {
            throw new PatrickException("Could not initalise task file " + filepath);
        } catch (InvalidPathException err) {
            throw new PatrickException("Invalid Filepath " + filepath);
        }
    }

    public TaskList load() throws PatrickException {
        TaskList tasks = new TaskList();
        // Go through every line and check if format is correct
        // Prompt user to enter the command (not stored format) if wrong format
        File file = new File(this.filepath);
        String corruptTaskString = "";
        try (Scanner scanner = new Scanner(file)) {
            while (true) {
                if (scanner.hasNext()) {
                    String taskString = scanner.nextLine();
                    try {
                        Task task = Parser.parseTaskFromString(taskString);
                        tasks.addTask(task);
                    } catch (PatrickException err) {
                        corruptTaskString = corruptTaskString + "\n\t" + err.getMessage();
                    }
                } else {
                    break;
                }
            }
        } catch (FileNotFoundException err) {
            throw new PatrickException("Save file not found!");
        }

        // Alert user if any corrupt Tasks skipped
        if (!("".equals(corruptTaskString))) {
            System.out.println("These tasks have been skipped due to file corruption:" + corruptTaskString);
            System.out.println("Patrick will proceed as usual without the above tasks.");
        }

        return tasks;
    }

    public void save(TaskList tasks) throws PatrickException {
        try {
            FileWriter fw = new FileWriter(this.filepath);
            for (int i = 0; i < tasks.getSize(); i++) {
                String taskString = Parser.getStringFromTask(tasks.getTask(i));
                fw.write(taskString + System.lineSeparator());
            }
            fw.close();
        } catch (IOException err) {
            throw new PatrickException("Error saving to file!");
        }
    }
}
