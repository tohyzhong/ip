package patrick.command;

import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.storage.Storage;
import patrick.task.TaskList;
import patrick.ui.Ui;

/**
 * Handles execution of delete command.
 * This class contains a single static function execute that parses user input
 * to remove a Task based on the input index.
 */
public class DeleteCommand {

    /**
     * Executes the delete command.
     * Expected {@code userInput} starts with "delete " + task index obtained from
     * {@code list} command.
     * 
     * @param tasks     The list containing the current tasks from which the task is
     *                  being deleted from.
     * @param ui        The user interface used to display feedback to the user.
     * @param userInput The raw user input string.
     * @param storage   The storage object used to save the task list after adding
     *                  the new deadline task.
     * @throws InvalidParameterException If the index is missing,
     *                                   not an integer, or out of bounds.
     * @throws PatrickException          If the task list is empty, or saving the
     *                                   task list fails with an error.
     */
    protected static void execute(TaskList tasks, Ui ui, String userInput, Storage storage) throws PatrickException {
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
            storage.save(tasks);
        } catch (NumberFormatException err) {
            throw new InvalidParameterException("Please enter a valid task number.");
        }
    }
}
