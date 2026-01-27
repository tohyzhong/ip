package patrick.command;

import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.task.TaskList;
import patrick.ui.Ui;

public class FindCommand {
    public static void execute(TaskList tasks, Ui ui, String userInput) throws PatrickException {
        String[] userInputArray;
        userInputArray = userInput.split(" ");
        if (userInputArray.length < 2) {
            throw new InvalidParameterException("Please enter a task.");
        } else if (tasks.getSize() < 1) {
            throw new PatrickException("There are no tasks.");
        }

        ui.display(tasks.findTasks(userInputArray[1]));
    }
}
