package patrick.command;

import patrick.exceptions.InvalidParameterException;
import patrick.exceptions.PatrickException;
import patrick.storage.Storage;
import patrick.tasks.TaskList;
import patrick.ui.Ui;

public class UnmarkCommand {
    public static void execute(TaskList tasks, Ui ui, String userInput, Storage storage) throws PatrickException {
        String[] userInputArray;
        userInputArray = userInput.split(" ");
        if (userInputArray.length < 2) {
            throw new InvalidParameterException("Please enter a task number.");
        } else if (tasks.getSize() < 1) {
            throw new PatrickException("There are no tasks.");
        } else if (Integer.parseInt(userInputArray[1]) > tasks.getSize()
                || Integer.parseInt(userInputArray[1]) < 1) {
            throw new InvalidParameterException(
                    "Please enter a valid task number between 1 and " + tasks.getSize());
        }
        ui.display(tasks.getTask(Integer.parseInt(userInputArray[1]) - 1).setNotDone());
        storage.save(tasks);
    }
}
