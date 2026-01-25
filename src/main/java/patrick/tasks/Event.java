package patrick.tasks;

public class Event extends Task {
    private String fromDate;
    private String toDate;

    public Event(String userInput, String fromString, String toString) {
        super(userInput);
        this.fromDate = fromString;
        this.toDate = toString;
    }

    public Event(String userInput, boolean isDone, String fromString, String toString) {
        super(userInput);
        this.isDone = isDone;
        this.fromDate = fromString;
        this.toDate = toString;
    }

    public String getFromDate() {
        return this.fromDate;
    }

    public String getToDate() {
        return this.toDate;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.isDone ? "X" : " ", this.description, this.fromDate, this.toDate);
    }
}
