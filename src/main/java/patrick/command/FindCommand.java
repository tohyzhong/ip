package patrick.command;

import java.util.Arrays;

import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.gui.Main;
import patrick.task.TaskList;

/**
 * Handles the execution of the find command.
 * This class parses the user input to extract a keyword and searches for
 * matching tasks.
 */
public class FindCommand {

    /**
     * Executes the find command by searching for tasks that contain the specified
     * search string.
     * Expected input format: find "search string".
     * The actual filtering logic is done in Tasklist findTasks(String).
     *
     * @param tasks     The task list to search within.
     * @param ui        The user interface to display the search results.
     * @param userInput The raw user input string.
     * @throws InvalidParameterException If no keyword is provided in the user
     *                                   input.
     * @throws PatrickException          If the task list empty.
     */
    public static void execute(TaskList tasks, Main gui, String userInput) throws PatrickException {
        String[] userInputArray;
        userInputArray = userInput.split(" ");
        if (userInputArray.length < 2) {
            throw new InvalidParameterException("Please enter a task.");
        } else if (tasks.getSize() < 1) {
            throw new PatrickException("There are no tasks.");
        }

        gui.display(tasks.findTasks(Arrays.copyOfRange(userInputArray, 1, userInputArray.length)));
    }
}
