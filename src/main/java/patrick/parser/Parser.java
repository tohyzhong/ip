package patrick.parser;

import patrick.exceptions.PatrickException;
import patrick.tasks.Deadline;
import patrick.tasks.Event;
import patrick.tasks.Task;
import patrick.tasks.ToDo;

public class Parser {
    public static String getStringFromTask(Task task) {
        boolean isDone = task.isDone();
        // Format: Task Type | Is Done | Task Name | <Empty>/By/From | <Empty>/To
        if (task instanceof ToDo) {
            return String.format("T | %B | %s", isDone, task.getDescription());
        } else if (task instanceof Deadline) {
            Deadline t = (Deadline) task;
            return String.format("D | %B | %s | %s", isDone, t.getDescription(), t.getByDate());
        } else if (task instanceof Event) {
            Event t = (Event) task;
            return String.format("E | %B | %s | %s | %s",
                    isDone, t.getDescription(), t.getFromDate(), t.getToDate());
        } else {
            return "";
        }
    }

    public static Task parseTaskFromString(String taskString) throws PatrickException {
        // Parse string and create Task
        String[] taskStringArray = taskString.split(" \\| ");
        Task task;

        try {
            switch (taskStringArray[0]) {
            case "T":
                task = new ToDo(taskStringArray[2], Boolean.parseBoolean(taskStringArray[1]));
                break;
            case "D":
                task = new Deadline(taskStringArray[2], Boolean.parseBoolean(taskStringArray[1]), taskStringArray[3]);
                break;
            case "E":
                task = new Event(taskStringArray[2], Boolean.parseBoolean(taskStringArray[1]), taskStringArray[3],
                        taskStringArray[4]);
                break;
            default:
                throw new PatrickException("Unknown task type in " + taskString);
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new PatrickException("Corrupt task string: " + taskString);
        }

        return task;
    }
}
