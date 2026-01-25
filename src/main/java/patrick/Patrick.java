package patrick;

import patrick.command.Command;
import patrick.exceptions.InvalidParameterException;
import patrick.exceptions.PatrickException;
import patrick.tasks.TaskList;

public class Patrick {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private static final String FILEPATH = "./data/tasks.txt";

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
