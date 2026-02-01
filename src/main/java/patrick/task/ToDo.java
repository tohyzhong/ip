package patrick.task;

/**
 * Represents a ToDo task.
 * A ToDo task contains a description and completion status.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with given description.
     * The completion status defaults to false.
     *
     * @param userInput The task description.
     */
    public ToDo(String userInput) {
        super(userInput);
    }

    /**
     * Constructs a ToDo task with given description and completion status.
     *
     * @param userInput The task description.
     * @param isDone    The completion status of the task (true if done, false
     *                  otherwise).
     */
    public ToDo(String userInput, boolean isDone) {
        super(userInput);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the ToDo task.
     * The format includes "[T]", the completion status, and the task description.
     *
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
