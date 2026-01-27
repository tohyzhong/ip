package patrick.command;

import patrick.exception.PatrickException;
import patrick.storage.Storage;
import patrick.task.TaskList;
import patrick.ui.Ui;

/**
 * Represents the collection of executable commands supported by Patrick.
 * Each enum corresponds to a valid command, or an error.
 */
public enum Command {
    BYE,
    LIST,
    ERROR,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    DUE,
    ON,
    FIND;

    /**
     * Parses user input string and returns corresponding Command.
     * First term of input string is extracted and matched against pre-defined
     * Command enums (case-insensitive).
     * 
     * @param userInput The raw user input string.
     * @return The matching {@code Command} constant, or {@code ERROR} for invalid
     *         commands.
     */
    public static Command getCommand(String userInput) {
        try {
            return Command.valueOf(userInput.split(" ")[0].toUpperCase());
        } catch (IllegalArgumentException err) {
            return ERROR;
        }
    }

    /**
     * Executes the logic for the corresponding command.
     * Dispatcher method to call relevant Command method based on the enum constant.
     * 
     * @param tasks     The list containing the current tasks.
     * @param ui        The user interface object for displaying output.
     * @param userInput The raw user input string.
     * @param storage   The storage object used for loading and saving changes
     *                  locally.
     * @throws PatrickException If an error occurs during execution of any command
     *                          (e.g. file read and write errors, invalid command
     *                          parameters)
     */
    public void execute(TaskList tasks, Ui ui, String userInput, Storage storage) throws PatrickException {
        switch (this) {
        case BYE:
            break;
        case LIST:
            ListCommand.execute(tasks, ui, userInput);
            break;
        case ERROR:
            throw new PatrickException("Unknown command :((");
        case MARK:
            MarkCommand.execute(tasks, ui, userInput, storage);
            break;
        case UNMARK:
            UnmarkCommand.execute(tasks, ui, userInput, storage);
            break;
        case TODO:
            ToDoCommand.execute(tasks, ui, userInput, storage);
            break;
        case DEADLINE:
            DeadlineCommand.execute(tasks, ui, userInput, storage);
            break;
        case EVENT:
            EventCommand.execute(tasks, ui, userInput, storage);
            break;
        case DELETE:
            DeleteCommand.execute(tasks, ui, userInput, storage);
            break;
        case DUE:
            DueCommand.execute(tasks, ui, userInput);
            break;
        case ON:
            OnCommand.execute(tasks, ui, userInput);
            break;
        case FIND:
            FindCommand.execute(tasks, ui, userInput);
        }
    }
}
