package patrick.command;

import patrick.Ui;
import patrick.tasks.TaskList;

public class ListCommand {
    public static void execute(TaskList tasks, Ui ui, String userInput) {
        ui.display(tasks.getAllTasks());
    }
}
