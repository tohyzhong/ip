public class Deadline extends Task {
    private String datetimeString;

    public Deadline(String userInput, String datetimeString) {
        super(userInput);
        this.datetimeString = datetimeString;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.isDone ? "X" : " ", this.description, this.datetimeString);
    }
}
