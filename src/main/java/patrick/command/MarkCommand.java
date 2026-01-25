package patrick.command;

import patrick.Storage;
import patrick.Ui;
import patrick.exceptions.InvalidParameterException;
import patrick.exceptions.PatrickException;
import patrick.tasks.TaskList;

public class MarkCommand {
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
        ui.display(tasks.getTask(Integer.parseInt(userInputArray[1]) - 1).setDone());
        storage.save(tasks);
    }
}
