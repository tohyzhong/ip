package patrick.command;

import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.gui.MainWindow;
import patrick.storage.Storage;
import patrick.task.Deadline;
import patrick.task.TaskList;

/**
 * Handles execution of deadline command.
 * This class contains a single static function execute that parses user input
 * to create a new Deadline task.
 */
public class DeadlineCommand {

    /**
     * Executes the deadline command.
     * Expected {@code userInput} starts with "deadline " + task description + "/by"
     * delimiter with date in format YYYY-MM-DD.
     *
     * @param tasks     The list containing the current tasks to which the new
     *                  deadline task is being added to.
     * @param gui       The user interface used to display feedback to the user.
     * @param userInput The raw user input string.
     * @param storage   The storage object used to save the task list after adding
     *                  the new deadline task.
     * @throws InvalidParameterException If the descriiption is empty, command
     *                                   parameter "/by" is missing, or Date format
     *                                   is not YYYY-MM-DD.
     * @throws PatrickException          If saving the task list fails with an
     *                                   error.
     */
    protected static void execute(TaskList tasks, MainWindow gui, String userInput, Storage storage)
            throws PatrickException {
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
            gui.display("Got it. I've added this task: \n\t"
                    + tasks.addTask(new Deadline(userInputArray[0], userInputArray[1])).toString()
                    + String.format("\nNow you have %d tasks in the list.", tasks.getSize()));
        } catch (java.time.format.DateTimeParseException e) {
            throw new InvalidParameterException("Please use YYYY-MM-DD.");
        }
        storage.save(tasks);
    }
}
