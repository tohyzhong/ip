package patrick.command;

import patrick.exceptions.InvalidParameterException;
import patrick.exceptions.PatrickException;
import patrick.storage.Storage;
import patrick.tasks.Deadline;
import patrick.tasks.TaskList;
import patrick.ui.Ui;

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

        try {
            ui.display("Got it. I've added this task: \n\t"
                    + tasks.addTask(new Deadline(userInputArray[0], userInputArray[1])).toString()
                    + String.format("\nNow you have %d tasks in the list.", tasks.getSize()));
        } catch (java.time.format.DateTimeParseException e) {
            throw new InvalidParameterException("Plase use YYYY-MM-DD.");
        }
        storage.save(tasks);
    }
}
