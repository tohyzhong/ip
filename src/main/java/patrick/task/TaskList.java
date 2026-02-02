package patrick.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a colletion of Tasks.
 * This class provides utility methods to add, delete, retrieve, and filter
 * tasks based on specific criteria.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to the list and returns the added task.
     *
     * @param task The Task object to be added.
     * @return The task that was just added.
     */
    public Task addTask(Task task) {
        this.tasks.add(task);
        return this.getTask(this.getSize() - 1);
    }

    /**
     * Deletes a task from the list at the specified index and returns the deleted
     * task.
     *
     * @param index The 0-based index of the task to be deleted.
     * @return The Task object that was deleted.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The 0-based index of the task to be retrieved.
     * @return The Task object at the specified position.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns a formatted string containing all tasks in the list.
     * Returns a notification message if the task list is empty.
     * Otherwise, returns a numbered list of all tasks.
     *
     * @return A formatted message representing the task list.
     */
    public String getAllTasks() {
        String str = "";
        if (this.getSize() == 0) {
            str = "There are no tasks!";
        } else {
            str = "Here are the tasks in your list:";
            for (int i = 0; i < this.getSize(); i++) {
                str = str + String.format("\n\t%d. %s", i + 1, this.getTask(i));
            }
        }

        return str;
    }

    /**
     * Filters and returns a formatted string of Deadline tasks due on a specified
     * Date.
     *
     * @param date The date to filter by.
     * @return A string containing the list of Deadline tasks matching the date.
     */
    public String getDueTasks(LocalDate date) {
        String str = String.format("These tasks are due on %s:",
                date.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
        int count = 0;

        for (int i = 0; i < this.getSize(); i++) {
            Task task = this.getTask(i);
            if (task instanceof Deadline) {
                Deadline t = (Deadline) task;
                if (t.getByDate().equals(date)) {
                    count++;
                    str = str + String.format("\n\t%d. %s", i + 1, t);
                }
            }
        }

        if (count == 0) {
            return "There are no tasks due!";
        } else {
            return str;
        }
    }

    /**
     * Filters and returns a formatted string of Event tasks occurring on the
     * specified date.
     *
     * @param date The date to filter by.
     * @return A string representation of the list of events on the date.
     */
    public String getEventsOn(LocalDate date) {
        String str = String.format("These events are on %s:",
                date.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
        int count = 0;

        for (int i = 0; i < this.getSize(); i++) {
            Task task = this.getTask(i);
            if (task instanceof Event) {
                Event t = (Event) task;
                if ((t.getFromDate().compareTo(date) <= 0) && (t.getToDate().compareTo(date) >= 0)) {
                    count++;
                    str = str + String.format("\n\t%d. %s", i + 1, t);
                }
            }
        }

        if (count == 0) {
            return "There are no events!";
        } else {
            return str;
        }
    }

    /**
     * Filters and returns a formatted string of Tasks that contain any part of the
     * search string in the description.
     *
     * @param searchStringArray The array of strings to filter by.
     * @return A string representation of the list of matching tasks.
     */
    public String findTasks(String... searchStringArray) {
        String str = "Here are the filtered tasks:";
        int count = 0;
        String[] searchStringArrayProcessed = new String[searchStringArray.length];
        for (int i = 0; i < searchStringArray.length; i++) {
            searchStringArrayProcessed[i] = searchStringArray[i].toLowerCase();
        }

        for (int i = 0; i < this.getSize(); i++) {
            Task task = this.getTask(i);
            String description = task.getDescription().toLowerCase();

            for (String searchString : searchStringArrayProcessed) {
                if (description.contains(searchString)) {
                    count++;
                    str = str + String.format("\n\t%d. %s", i + 1, task);
                }
            }
        }

        if (count == 0) {
            return "There are no tasks matching your description!";
        } else {
            return str;
        }
    }
}
