package patrick.command;

import patrick.Ui;
import patrick.exceptions.InvalidParameterException;
import patrick.exceptions.PatrickException;
import patrick.tasks.TaskList;

public class DeleteCommand {
    public static void execute(TaskList tasks, Ui ui, String userInput) throws PatrickException {
        String[] userInputArray;
        try {
            userInputArray = userInput.split(" ");
            if (userInputArray.length < 2) {
                throw new InvalidParameterException("Please enter a task number.");
            } else if (tasks.getSize() < 1) {
                throw new PatrickException("There are no tasks.");
            } else if (Integer.parseInt(userInputArray[1]) > tasks.getSize()) {
                throw new InvalidParameterException(
                        "Please enter a valid task number between 1 and " + tasks.getSize());
            }

            ui.display("Noted. I've removed this task: \n\t"
                    + tasks.deleteTask(Integer.parseInt(userInputArray[1]) - 1).toString()
                    + String.format("\nNow you have %d tasks in the list.", tasks.getSize()));
        } catch (NumberFormatException err) {
            throw new InvalidParameterException("Please enter a valid task number.");
        }
    }
}
