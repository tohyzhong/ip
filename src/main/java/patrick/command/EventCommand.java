package patrick.command;

import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.storage.Storage;
import patrick.task.Event;
import patrick.task.TaskList;
import patrick.ui.Ui;

public class EventCommand {
    public static void execute(TaskList tasks, Ui ui, String userInput, Storage storage) throws PatrickException {
        String[] userInputArray;
        if (userInput.length() <= 6) {
            throw new InvalidParameterException("Please enter a task name.");
        }

        userInput = userInput.substring(6);
        userInputArray = userInput.split("\\s+/(from|to)\\s+");

        if (userInputArray.length < 3) {
            throw new InvalidParameterException(
                    "Command parameters are missing. Do you have /from and /to ?");
        }

        try {
            ui.display("Got it. I've added this task: \n\t"
                    + tasks.addTask(new Event(userInputArray[0], userInputArray[1], userInputArray[2])).toString()
                    + String.format("\nNow you have %d tasks in the list.", tasks.getSize()));
        } catch (java.time.format.DateTimeParseException e) {
            throw new InvalidParameterException("Plase use YYYY-MM-DD.");
        }

        storage.save(tasks);
    }
}
