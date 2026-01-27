package patrick.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent a Task with a specific start and end date.
 * Event task includes a description, start and end dates stored as
 * {@Link LocalDate}.
 */
public class Event extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Constructs a new Event task with the specified description, start date, and
     * end date.
     * 
     * @param userInput  The description of the task.
     * @param fromString The specified start date YYYY-MM-DD.
     * @param toString   The specified end date YYYY-MM-DD.
     */
    public Event(String userInput, String fromString, String toString) {
        super(userInput);
        this.fromDate = LocalDate.parse(fromString);
        this.toDate = LocalDate.parse(toString);
    }

    /**
     * Constructs a new Event task with the specified description, completion
     * status, start date, and end date.
     * 
     * @param userInput  The description of the task.
     * @param isDone     The completion status of the task.
     * @param fromString The specified start date YYYY-MM-DD.
     * @param toString   The specified end date YYYY-MM-DD.
     */
    public Event(String userInput, boolean isDone, String fromString, String toString) {
        super(userInput);
        this.isDone = isDone;
        this.fromDate = LocalDate.parse(fromString);
        this.toDate = LocalDate.parse(toString);
    }

    /**
     * Returns the start date of the Event task.
     * 
     * @return The {@Link LocalDate} object representing the start date.
     */
    public LocalDate getFromDate() {
        return this.fromDate;
    }

    /**
     * Returns the end date of the Event task.
     * 
     * @return The {@Link LocalDate} object representing the end date.
     */
    public LocalDate getToDate() {
        return this.toDate;
    }

    /**
     * Returns a string representation of the event task.
     * The output includes "[E]", the completion status, the description, and the
     * formatted from and to dates in d MMM yyyy.
     * 
     * @return A formatted string representing the Event task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.isDone ? "X" : " ",
                this.description,
                this.fromDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                this.toDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
