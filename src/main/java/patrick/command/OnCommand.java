package patrick.command;

import java.time.LocalDate;

import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.task.TaskList;
import patrick.ui.Ui;

public class OnCommand {
    public static void execute(TaskList tasks, Ui ui, String userInput) throws PatrickException {
        LocalDate date;
        String[] userInputArray;
        userInputArray = userInput.split(" ");
        if (userInputArray.length < 2) {
            throw new InvalidParameterException("Please enter a date.");
        } else if (tasks.getSize() < 1) {
            throw new PatrickException("There are no tasks.");
        }

        try {
            date = LocalDate.parse(userInputArray[1]);
        } catch (java.time.format.DateTimeParseException e) {
            throw new InvalidParameterException("Plase use YYYY-MM-DD.");
        }

        ui.display(tasks.getEventsOn(date));
    }
}
