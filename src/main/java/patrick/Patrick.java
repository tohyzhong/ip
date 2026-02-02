package patrick;

import patrick.command.Command;
import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.gui.Main;
import patrick.storage.Storage;
import patrick.task.TaskList;

/**
 * The main class for Patrick.
 * This class coordinates the interactions between the TaskList, Ui, Storage,
 * and Command components.
 */
public class Patrick {
    public static final String BOT_NAME = "P4Tr1CK";
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

    public void handleUserInput(String userInput, Main gui) {
        try {
            Command cmd = Command.getCommand(userInput);
            cmd.execute(this.tasks, gui, userInput, this.storage);
        } catch (InvalidParameterException err) {
            gui.displayParamError(err.getMessage());
        } catch (PatrickException err) {
            gui.displayError(err.getMessage());
        }

    }
}
