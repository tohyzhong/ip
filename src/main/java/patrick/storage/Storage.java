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

import patrick.Patrick;
import patrick.exception.PatrickException;
import patrick.parser.Parser;
import patrick.task.Task;
import patrick.task.TaskList;

/**
 * Manages the loading and saving of the task list locally.
 * This class handles the I/O operations, including creating directories and
 * files, and translating from text to {@code Tasklist}, and vice-versa.
 */
public class Storage {
    private String filepath;

    /**
     * Initialises a Storage object and ensures the data file exists.
     * If the specified file or path does not exist, the constructor creates them.
     *
     * @param filepath The path to the save file.
     * @throws PatrickException If the filepath is invalid or if there is an I/O
     *                          error during file/directory creation.
     */
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

    /**
     * Loads tasks from the local save file into a {@code TaskList}.
     * This method does a line by line read, and checks for corrupt string and
     * formatting issues. Problematic lines are skipped and a summary of corrupt
     * strings is presented to the user.
     * The clean list is then saved back to the file.
     *
     * @return A {@code TaskList} containing all valid tasks loaded from the save
     *         file.
     * @throws PatrickException if the save file cannot be found or read.
     */
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
            System.out.println("These tasks have been removed due to file corruption:" + corruptTaskString);
            System.out.println(String.format("%s will proceed as usual without the above tasks.", Patrick.BOT_NAME));
        }

        this.save(tasks);

        return tasks;
    }

    /**
     * Saves the current {@code Tasklist} to the local save file.
     * Converts each task into the storage string format and writes to the save
     * file.
     *
     * @param tasks The {@code Tasklist} containing tasks to save.
     * @throws PatrickException If saving to the local save file is met with an
     *                          error.
     */
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
