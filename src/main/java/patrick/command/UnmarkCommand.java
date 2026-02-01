package patrick.command;

import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.storage.Storage;
import patrick.task.TaskList;
import patrick.ui.Ui;

/**
 * Handles the execution of unmark command.
 * This class contains a single static function execute that parses user input
 * to unmark a task as done, and updates storage.
 */
public class UnmarkCommand {

    /**
     * Executes the unmark command by parsing the task number and setting the task
     * as not done.
     * Expected {@code userInput} in the format: unmark "task number".
     *
     * @param tasks     The list that contains the task to be marked not done.
     * @param ui        The user interface used to display feedback to the user.
     * @param userInput The raw user input string.
     * @param storage   The storage used to save the updated task list.
     * @throws InvalidParameterException If the index is missing, not a valid
     *                                   integer, or falls outside the range [1,
     *                                   tasks.getSize()].
     * @throws PatrickException          If the task list is empty or a storage
     *                                   write error occurs.
     */
    protected static void execute(TaskList tasks, Ui ui, String userInput, Storage storage) throws PatrickException {
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
