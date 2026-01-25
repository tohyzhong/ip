package patrick.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate byDate;

    public Deadline(String userInput, String datetimeString) {
        super(userInput);
        this.byDate = LocalDate.parse(datetimeString);
    }

    public Deadline(String userInput, boolean isDone, String datetimeString) {
        super(userInput);
        this.isDone = isDone;
        this.byDate = LocalDate.parse(datetimeString);
    }

    public LocalDate getByDate() {
        return this.byDate;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.isDone ? "X" : " ",
                this.description,
                this.byDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
