package patrick.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent a Task with a specific deadline.
 * Deadline task includes a description, and a due date stored as a
 * {@Link LocalDate}.
 */
public class Deadline extends Task {
    private LocalDate byDate;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     * 
     * @param userInput      The description of the task.
     * @param datetimeString The specified due date YYYY-MM-DD.
     */
    public Deadline(String userInput, String datetimeString) {
        super(userInput);
        this.byDate = LocalDate.parse(datetimeString);
    }

    /**
     * Constructs a new Deadline task with the specified description, completion
     * status, and due date.
     * 
     * @param userInput      The description of the task.
     * @param isDone         The completion status of the task.
     * @param datetimeString The specified due date YYYY-MM-DD.
     */
    public Deadline(String userInput, boolean isDone, String datetimeString) {
        super(userInput);
        this.isDone = isDone;
        this.byDate = LocalDate.parse(datetimeString);
    }

    /**
     * Returns the due date of the Deadline task.
     * 
     * @return The {@Link LocalDate} object representing the deadline.
     */
    public LocalDate getByDate() {
        return this.byDate;
    }

    /**
     * Returns a string representation of the deadline task.
     * The output includes "[D]", the completion status, the description, and the
     * formatted date in d MMM yyyy.
     * 
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.isDone ? "X" : " ",
                this.description,
                this.byDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
