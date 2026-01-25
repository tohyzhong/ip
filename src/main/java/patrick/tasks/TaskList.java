package patrick.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task addTask(Task task) {
        this.tasks.add(task);
        return this.getTask(this.getSize() - 1);
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

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
}
