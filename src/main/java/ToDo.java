public class ToDo extends Task {
    public ToDo(String userInput) {
        super(userInput);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
