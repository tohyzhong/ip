package patrick.tasks;

public abstract class Task {
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

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public abstract String toString();
}
