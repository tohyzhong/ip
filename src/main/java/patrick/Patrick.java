package patrick;

import patrick.command.Command;
import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.gui.MainWindow;
import patrick.storage.Storage;
import patrick.task.TaskList;

/**
 * The main class for Patrick.
 * This class coordinates the interactions between the TaskList, Ui, Storage,
 * and Command components.
 */
public class Patrick {
    public static final String BOT_NAME = "Patrick";
    private static final String FILEPATH = "./data/tasks.txt";
    private TaskList tasks;
    private Storage storage;

    /**
     * Attempts to load existing tasks from a local save file.
     * If loading fails, an empty TaskList is created instead.
     *
     * @return A string providing user with feedback on loading the save file.
     */
    public String init() {
        String response = "Task List loaded successfully!";
        try {
            this.storage = new Storage(Patrick.FILEPATH);
            this.tasks = this.storage.load();
        } catch (PatrickException err) {
            this.tasks = new TaskList();
            response = err.getMessage() + "\nInitialising empty task list...";
        }

        return response;
    }

    /**
     * Processes the raw string input from the user and coordinates the execution of
     * the corresponding command.
     * This method parses the input to identify a {@code Command}, then attempts to
     * execute it using the current task list, GUI, and storage.
     *
     * @param userInput The raw string entered by the user in the GUI.
     * @param gui       THE {@code MainWindow} instance used to interact with the
     *                  user interface.
     */
    public void handleUserInput(String userInput, MainWindow gui) {
        try {
            Command cmd = Command.getCommand(userInput);
            cmd.execute(this.tasks, gui, userInput, this.storage);
        } catch (InvalidParameterException err) {
            gui.displayParameterError(err.getMessage());
        } catch (PatrickException err) {
            gui.displayError(err.getMessage());
        }

    }
}
