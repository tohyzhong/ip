package patrick.tasks;

public class Event extends Task {
    private String fromString;
    private String toString;

    public Event(String userInput, String fromString, String toString) {
        super(userInput);
        this.fromString = fromString;
        this.toString = toString;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.isDone ? "X" : " ", this.description, this.fromString, this.toString);
    }
}
