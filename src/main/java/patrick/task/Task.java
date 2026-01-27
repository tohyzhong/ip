package patrick.task;

/**
 * Represents an abstract task in Patrick.
 * This class provides the base functionality for all tasks, including a
 * description and a completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description. The task is initailised as
     * not done.
     * 
     * @param description The text description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     * 
     * @return A confirmation message including the string representation of the
     *         task.
     */
    public String setDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n\t%s", this.toString());
    }

    /**
     * Marks the task as not completed.
     * 
     * @return A confirmation message including the string representation of the
     *         task.
     */
    public String setNotDone() {
        this.isDone = false;
        return String.format("OK, I've marked this task as not done yet:\n\t%s", this.toString());
    }

    /**
     * Returns the completion status of the task.
     * 
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     * 
     * @return The task description string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task.
     * This method must be implemented by subclasses.
     * 
     * @return A formatted string representing the task.
     */
    @Override
    public abstract String toString();
}
