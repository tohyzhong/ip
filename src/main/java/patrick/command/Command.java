package patrick.command;

import patrick.exceptions.PatrickException;
import patrick.storage.Storage;
import patrick.tasks.TaskList;
import patrick.ui.Ui;

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
    ON;

    public static Command getCommand(String userInput) {
        try {
            return Command.valueOf(userInput.split(" ")[0].toUpperCase());
        } catch (IllegalArgumentException err) {
            return ERROR;
        }
    }

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
        }
    }
}
