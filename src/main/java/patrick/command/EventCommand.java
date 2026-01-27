package patrick.command;

import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.storage.Storage;
import patrick.task.Event;
import patrick.task.TaskList;
import patrick.ui.Ui;

/**
 * Handles the execution of event command.
 * This class contains a single static function execute that parses user input
 * to create an Event task with user specified start and end date, adds to task
 * list, and updates storage.
 */
public class EventCommand {

    /**
     * Executes the event command by parsing the description, start date, end date.
     * Expected {@code userInput} in the format: event <description> /from
     * YYYY-MM-DD /to YYYY-MM-DD.
     * Regex is used to extract the description, start date, and end date.
     * 
     * @param tasks     The list to which the new Event will be added to.
     * @param ui        The user interface used to display feedback to the user.
     * @param userInput The raw user input string.
     * @param storage   The storage used to save the updated task list.
     * @throws InvalidParameterException If the name is missing, /from or /to tags
     *                                   are
     *                                   missing, or if date formats are invalid.
     * @throws PatrickException          If an error occurs while saving to the
     *                                   file.
     */
    protected static void execute(TaskList tasks, Ui ui, String userInput, Storage storage) throws PatrickException {
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
