package patrick.command;

import java.time.LocalDate;

import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.task.TaskList;
import patrick.ui.Ui;

/**
 * Handles execution of on command.
 * This class contains a single static function execute that parses user input
 * to filter and display all Event tasks that are occurring on the provided
 * Date.
 */
public class OnCommand {

    /**
     * Executes the on command by parsing the Date, and filtering events that fall
     * on the date.
     * Expected {@code userInput} in the format: on YYYY-MM-DD.
     * 
     * @param tasks     The list that is being filtered using the provided date.
     * @param ui        The user interface used to display feedback to the user.
     * @param userInput The raw user input string.
     * @throws InvalidParameterException If the date is missing, or if date format
     *                                   is invalid.
     * @throws PatrickException          If there are no tasks.
     */
    protected static void execute(TaskList tasks, Ui ui, String userInput) throws PatrickException {
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

        ui.display(tasks.getEventsOn(date));
    }
}
