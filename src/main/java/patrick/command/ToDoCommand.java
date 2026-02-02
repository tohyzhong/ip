package patrick.command;

import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.gui.Main;
import patrick.storage.Storage;
import patrick.task.TaskList;
import patrick.task.ToDo;

/**
 * Handles the execution of todo command.
 * This class contains a single static function execute that parses user input
 * to create an ToDo task with user specified task description, adds to task
 * list, and updates storage.
 */
public class ToDoCommand {

    /**
     * Executes the todo command by parsing the description, adding to task list and
     * saving to storage.
     * Expected {@code userInput} in the format: todo "description".
     *
     * @param tasks     The list to which the new ToDo task will be added to.
     * @param ui        The user interface used to display feedback to the user.
     * @param userInput The raw user input string.
     * @param storage   The storage used to save the updated task list.
     * @throws InvalidParameterException If the name is missing.
     * @throws PatrickException          If an error occurs while saving to the
     *                                   file.
     */
    protected static void execute(TaskList tasks, Main gui, String userInput, Storage storage) throws PatrickException {
        if (userInput.length() <= 5) {
            throw new InvalidParameterException("Please enter a task name.");
        }

        userInput = userInput.substring(5);
        gui.display("Got it. I've added this task: \n\t"
                + tasks.addTask(new ToDo(userInput)).toString()
                + String.format("\nNow you have %d tasks in the list.", tasks.getSize()));
        storage.save(tasks);
    }
}
