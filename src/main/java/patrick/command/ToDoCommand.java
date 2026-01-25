package patrick.command;

import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.storage.Storage;
import patrick.task.TaskList;
import patrick.task.ToDo;
import patrick.ui.Ui;

public class ToDoCommand {
    public static void execute(TaskList tasks, Ui ui, String userInput, Storage storage) throws PatrickException {
        if (userInput.length() <= 5) {
            throw new InvalidParameterException("Please enter a task name.");
        }

        userInput = userInput.substring(5);
        ui.display("Got it. I've added this task: \n\t"
                + tasks.addTask(new ToDo(userInput)).toString()
                + String.format("\nNow you have %d tasks in the list.", tasks.getSize()));
        storage.save(tasks);
    }
}
