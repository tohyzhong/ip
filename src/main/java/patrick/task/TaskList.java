package patrick.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a collection of Tasks.
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
        assert this.tasks != null : "Task list should be initialised properly";
    }

    /**
     * Adds a task to the list and returns the added task.
     *
     * @param task The Task object to be added.
     * @return The task that was just added.
     */
    public Task addTask(Task task) {
        this.tasks.add(task);
        assert this.tasks.contains(task) : "Task should be added to the list";
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
        if (this.getSize() == 0) {
            return "There are no tasks!";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < this.getSize(); i++) {
            sb.append(String.format("\n\t%d. %s", i + 1, this.getTask(i)));
        }

        return sb.toString();
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
     * search string in the description, using fuzzy matching.
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
            String[] words = description.split("\\s+");

            boolean matched = false;
            for (String searchString : searchStringArrayProcessed) {
                for (String word : words) {
                    if (word.contains(searchString) || TaskList.levenshteinDistance(word, searchString) <= 2) {
                        matched = true;
                        break;
                    }
                }
                if (matched) {
                    break;
                }
            }
            if (matched) {
                count++;
                str = str + String.format("\n\t%d. %s", i + 1, task);
            }
        }

        if (count == 0) {
            return "There are no tasks matching your description!";
        } else {
            return str;
        }
    }

    private static int levenshteinDistance(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }
        return dp[len1][len2];
    }
}
