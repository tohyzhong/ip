package patrick.task;

public class ToDo extends Task {
    public ToDo(String userInput) {
        super(userInput);
    }

    public ToDo(String userInput, boolean isDone) {
        super(userInput);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
