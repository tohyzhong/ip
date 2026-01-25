package patrick.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    public Event(String userInput, String fromString, String toString) {
        super(userInput);
        this.fromDate = LocalDate.parse(fromString);
        this.toDate = LocalDate.parse(toString);
    }

    public Event(String userInput, boolean isDone, String fromString, String toString) {
        super(userInput);
        this.isDone = isDone;
        this.fromDate = LocalDate.parse(fromString);
        this.toDate = LocalDate.parse(toString);
    }

    public LocalDate getFromDate() {
        return this.fromDate;
    }

    public LocalDate getToDate() {
        return this.toDate;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.isDone ? "X" : " ",
                this.description,
                this.fromDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                this.toDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
