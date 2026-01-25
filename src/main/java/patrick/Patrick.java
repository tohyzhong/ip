package patrick;

import patrick.command.Command;
import patrick.exceptions.InvalidParameterException;
import patrick.exceptions.PatrickException;
import patrick.tasks.TaskList;

public class Patrick {
    private TaskList tasks;
    private Ui ui;

    public Patrick() {
        this.tasks = new TaskList();
        this.ui = new Ui();
    }

    public void run() {
        this.ui.displayWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = this.ui.readInput();
                Command cmd = Command.getCommand(userInput);
                cmd.execute(this.tasks, this.ui, userInput);
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
