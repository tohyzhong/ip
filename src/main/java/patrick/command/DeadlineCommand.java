package patrick.command;

import patrick.Storage;
import patrick.Ui;
import patrick.exceptions.InvalidParameterException;
import patrick.exceptions.PatrickException;
import patrick.tasks.Deadline;
import patrick.tasks.TaskList;

public class DeadlineCommand {
    public static void execute(TaskList tasks, Ui ui, String userInput, Storage storage) throws PatrickException {
        String[] userInputArray;
        if (userInput.length() <= 9) {
            throw new InvalidParameterException("Please enter a task name.");
        }

        userInput = userInput.substring(9);
        userInputArray = userInput.split("\\s+/by\\s+");

        if (userInputArray.length < 2) {
            throw new InvalidParameterException(
                    "Command parameters are missing. Do you have /by ?");
        }

        ui.display("Got it. I've added this task: \n\t"
                + tasks.addTask(new Deadline(userInputArray[0], userInputArray[1])).toString()
                + String.format("\nNow you have %d tasks in the list.", tasks.getSize()));
        storage.save(tasks);
    }
}
