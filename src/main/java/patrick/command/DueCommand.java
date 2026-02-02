package patrick.command;

import java.time.LocalDate;

import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.gui.MainWindow;
import patrick.task.TaskList;

/**
 * Handles execution of due command.
 * This class contains a single static function execute that parses user input
 * to filter and display all Deadline tasks that are due on the provided Date.
 */
public class DueCommand {

    /**
     * Executes the due command by filtering the task list for a specific date.
     * Expected {@code userInput} starts with "due " + Date in format "YYYY-MM-DD".
     *
     * @param tasks     The list containing the current tasks that is being
     *                  filtered.
     * @param gui       The user interface used to display feedback to the user.
     * @param userInput The raw user input string.
     * @throws InvalidParameterException If the date is missing or not in YYYY-MM-DD
     *                                   format.
     * @throws PatrickException          If the task list is currently empty.
     */
    protected static void execute(TaskList tasks, MainWindow gui, String userInput) throws PatrickException {
        LocalDate date;
        String[] userInputArray;
        userInputArray = userInput.split(" ");
        if (userInputArray.length < 2) {
            throw new InvalidParameterException("Please enter a date.");
        } else if (tasks.getSize() < 1) {
            throw new PatrickException("There are no tasks.");
        }

        try {
            date = LocalDate.parse(userInputArray[1]);
        } catch (java.time.format.DateTimeParseException e) {
            throw new InvalidParameterException("Plase use YYYY-MM-DD.");
        }

        gui.display(tasks.getDueTasks(date));
    }
}
