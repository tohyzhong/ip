package patrick.command;

import patrick.tasks.TaskList;
import patrick.ui.Ui;

public class ListCommand {
    public static void execute(TaskList tasks, Ui ui, String userInput) {
        ui.display(tasks.getAllTasks());
    }
}
