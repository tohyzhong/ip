package patrick.command;

import patrick.gui.Main;
import patrick.task.TaskList;

/**
 * Handles the execution of list command.
 * This class contains a single static function execute that obtains a String
 * representation of all tasks in the task list and outputs through the user
 * interface.
 */
public class ListCommand {

    /**
     * Executes the list command by getting a String representation of all tasks in
     * the task list, and output through the provided user interface.
     *
     * @param tasks     The list of existing tasks.
     * @param ui        The user interface used to display the task list.
     * @param userInput The raw user input string.
     */
    protected static void execute(TaskList tasks, Main gui, String userInput) {
        gui.display(tasks.getAllTasks());
    }
}
