package patrick.command;

import patrick.Ui;
import patrick.exceptions.PatrickException;
import patrick.tasks.TaskList;

public enum Command {
    BYE,
    LIST,
    ERROR,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE;

    public static Command getCommand(String userInput) {
        try {
            return Command.valueOf(userInput.split(" ")[0].toUpperCase());
        } catch (IllegalArgumentException err) {
            return ERROR;
        }
    }

    public void execute(TaskList tasks, Ui ui, String userInput) throws PatrickException {
        switch (this) {
        case BYE:
            break;
        case LIST:
            ListCommand.execute(tasks, ui, userInput);
            break;
        case ERROR:
            throw new PatrickException("Unknown command :((");
        case MARK:
            MarkCommand.execute(tasks, ui, userInput);
            break;
        case UNMARK:
            UnmarkCommand.execute(tasks, ui, userInput);
            break;
        case TODO:
            ToDoCommand.execute(tasks, ui, userInput);
            break;
        case DEADLINE:
            DeadlineCommand.execute(tasks, ui, userInput);
            break;
        case EVENT:
            EventCommand.execute(tasks, ui, userInput);
            break;
        case DELETE:
            DeleteCommand.execute(tasks, ui, userInput);
            break;
        }
    }
}
