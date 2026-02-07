package patrick.parser;

import patrick.exception.PatrickException;
import patrick.task.Deadline;
import patrick.task.Event;
import patrick.task.Task;
import patrick.task.ToDo;

/**
 * Deals with making sense of user commands and saved data strings.
 * This class provides utility methods to convert {@code Task} objects into
 * formatted strings for storage, and to parse those saved strings back into
 * {@code Task} objects.
 */
public class Parser {

    /**
     * Converts a {@code Task} object into a formatted string for file storage.
     * Format: Task Type | Is Done | Task Name | Empty/By/From | Empty/To
     *
     * @param task The task to be converted.
     * @return A '|' delimited string representing the task, or empty string if task
     *         type is unknown.
     */
    public static String getStringFromTask(Task task) {
        boolean isDone = task.isDone();
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

    /**
     * Parses a formatted storage string into a Task object (ToDo, Deadline, or
     * Event).
     *
     * @param taskString The formatted line from the storage file.
     * @return A {@code Task} object corresponding to the string data.
     * @throws PatrickException If the string is corrupt, has missing fields,
     *                          unrecognised Task type, or invalid Date format.
     */
    public static Task parseTaskFromString(String taskString) throws PatrickException {
        // Parse string and create Task
        String[] taskStringArray = taskString.split(" \\| ");
        Task task;

        if (taskStringArray.length < 3) {
            throw new PatrickException("Corrupt task string: " + taskString);
        }

        assert taskStringArray.length >= 3 : "Task string array should have at least 3 elements";

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
        } catch (java.time.format.DateTimeParseException e) {
            throw new PatrickException("Corrupt Date: " + taskString);
        }

        return task;
    }
}
