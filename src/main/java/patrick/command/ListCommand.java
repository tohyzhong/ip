package patrick.command;

import patrick.gui.MainWindow;
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
     * @param gui       The user interface used to display the task list.
     * @param userInput The raw user input string.
     */
    protected static void execute(TaskList tasks, MainWindow gui, String userInput) {
        assert tasks != null : "TaskList cannot be null";
        assert gui != null : "MainWindow cannot be null";
        assert userInput != null : "User input cannot be null";
        gui.display(tasks.getAllTasks());
    }
}
