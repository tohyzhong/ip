package patrick.command;

import patrick.Ui;
import patrick.exceptions.InvalidParameterException;
import patrick.exceptions.PatrickException;
import patrick.tasks.TaskList;
import patrick.tasks.ToDo;

public class ToDoCommand {
    public static void execute(TaskList tasks, Ui ui, String userInput) throws PatrickException {
        if (userInput.length() <= 5) {
            throw new InvalidParameterException("Please enter a task name.");
        }

        userInput = userInput.substring(5);
        ui.display("Got it. I've added this task: \n\t"
                + tasks.addTask(new ToDo(userInput)).toString()
                + String.format("\nNow you have %d tasks in the list.", tasks.getSize()));
    }
}
