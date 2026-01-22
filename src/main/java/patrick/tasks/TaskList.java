package patrick.tasks;

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
}
