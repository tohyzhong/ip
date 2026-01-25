package patrick.tasks;

public class Deadline extends Task {
    private String byDate;

    public Deadline(String userInput, String datetimeString) {
        super(userInput);
        this.byDate = datetimeString;
    }

    public Deadline(String userInput, boolean isDone, String datetimeString) {
        super(userInput);
        this.isDone = isDone;
        this.byDate = datetimeString;
    }

    public String getByDate() {
        return this.byDate;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.isDone ? "X" : " ", this.description, this.byDate);
    }
}
