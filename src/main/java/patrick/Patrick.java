package patrick;

import patrick.command.Command;
import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.storage.Storage;
import patrick.task.TaskList;
import patrick.ui.Ui;

/**
 * The main class for Patrick.
 * This class coordinates the interactions between the TaskList, Ui, Storage,
 * and Command components.
 */
public class Patrick {
    public static final String BOT_NAME = "P4Tr1CK";
    private static final String FILEPATH = "./data/tasks.txt";
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Initialises a new Patrick instance.
     * Sets up the UI and attempts to load existing tasks from a local save file.
     * If loading fails, an empty TaskList is created instead.
     */
    public Patrick() {
        this.ui = new Ui();

        try {
            this.storage = new Storage(Patrick.FILEPATH);
            this.tasks = this.storage.load();
        } catch (PatrickException err) {
            this.tasks = new TaskList();
            this.ui.displayError(err.getMessage() + "\nInitialising empty task list...");
        }
    }

    /**
     * The main program loop.
     * A welcome message is displayed at the start, before repeatedly parsing user
     * input and executing user given commands until the "bye" command.
     * Class specific exceptions are handled to provide feedback through the UI.
     */
    public void run() {
        this.ui.displayWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = this.ui.readInput();
                Command cmd = Command.getCommand(userInput);
                cmd.execute(this.tasks, this.ui, userInput, this.storage);
                isExit = cmd == Command.BYE;
            } catch (InvalidParameterException err) {
                this.ui.displayParamError(err.getMessage());
            } catch (PatrickException err) {
                this.ui.displayError(err.getMessage());
            }
        }

        this.ui.endUi();
    }

    public static void main(String[] args) {
        Patrick bot = new Patrick();
        bot.run();
    }

}
