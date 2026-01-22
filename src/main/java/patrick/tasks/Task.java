package patrick.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String setDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n\t%s", this.toString());
    }

    public String setNotDone() {
        this.isDone = false;
        return String.format("OK, I've marked this task as not done yet:\n\t%s", this.toString());
    }

    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}