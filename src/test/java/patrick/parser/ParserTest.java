package patrick.parser;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import patrick.exception.PatrickException;
import patrick.task.Deadline;
import patrick.task.Event;
import patrick.task.Task;
import patrick.task.ToDo;

public class ParserTest {

    // Mock classes to isolate tests from real implementations
    private static class MockToDo extends ToDo {
        public MockToDo(String description, boolean isDone) {
            super(description);
            if (isDone) {
                this.setDone();
            }
        }
    }

    private static class MockDeadline extends Deadline {
        public MockDeadline(String description, boolean isDone, String by) throws Exception {
            super(description, by);
            if (isDone) {
                this.setDone();
            }
        }
    }

    private static class MockEvent extends Event {
        public MockEvent(String description, boolean isDone, String from, String to) throws Exception {
            super(description, from, to);
            if (isDone) {
                this.setDone();
            }
        }
    }

    @Test
    public void getStringFromTask_todo_returnsCorrectFormat() {
        ToDo todo = new MockToDo("Sleep 8 hours", true);
        String expected = "T | TRUE | Sleep 8 hours";
        Assertions.assertEquals(expected, Parser.getStringFromTask(todo));
    }

    @Test
    public void getStringFromTask_deadline_returnsCorrectFormat() throws Exception {
        Deadline deadline = new MockDeadline("Assignment 1", true, "2026-01-27");
        String expected = "D | TRUE | Assignment 1 | 2026-01-27";
        Assertions.assertEquals(expected, Parser.getStringFromTask(deadline));
    }

    @Test
    public void getStringFromTask_event_returnsCorrectFormat() throws Exception {
        Event event = new MockEvent("Concert", false, "2026-01-27", "2026-01-28");
        String expected = "E | FALSE | Concert | 2026-01-27 | 2026-01-28";
        Assertions.assertEquals(expected, Parser.getStringFromTask(event));
    }

    @Test
    public void parseTaskFromString_validTodo_returnsTodo() throws PatrickException {
        String input = "T | TRUE | Vibe Code";
        Task result = Parser.parseTaskFromString(input);

        Assertions.assertTrue(result instanceof ToDo);
        Assertions.assertEquals("Vibe Code", result.getDescription());
        Assertions.assertTrue(result.isDone());
    }

    @Test
    public void parseTaskFromString_validDeadline_returnsDeadline() throws PatrickException {
        String input = "D | FALSE | Buy Spongebob | 2026-01-27";
        Task result = Parser.parseTaskFromString(input);

        Assertions.assertTrue(result instanceof Deadline);
        Assertions.assertEquals("Buy Spongebob", result.getDescription());
        Assertions.assertEquals(LocalDate.parse("2026-01-27"), ((Deadline) result).getByDate());
    }

    @Test
    public void parseTaskFromString_validEvent_returnsEvent() throws PatrickException {
        String input = "E | FALSE | Scold Mr Krabs | 2026-01-27 | 2026-01-28";
        Task result = Parser.parseTaskFromString(input);

        Assertions.assertTrue(result instanceof Event);
        Assertions.assertEquals("Scold Mr Krabs", result.getDescription());
        Assertions.assertEquals(LocalDate.parse("2026-01-27"), ((Event) result).getFromDate());
        Assertions.assertEquals(LocalDate.parse("2026-01-28"), ((Event) result).getToDate());
    }

    @Test
    public void parseTaskFromString_invalidString_throwsPatrickException() {
        String input = "T | TRUE";

        PatrickException exception = Assertions.assertThrows(PatrickException.class,
                () -> Parser.parseTaskFromString(input));
        Assertions.assertTrue(exception.getMessage().equals("Corrupt task string: " + input));
    }

    @Test
    public void parseTaskFromString_invalidDate_throwsPatrickException() {
        String input = "D | TRUE | Fake Task | 123456";

        PatrickException exception = Assertions.assertThrows(PatrickException.class,
                () -> Parser.parseTaskFromString(input));
        Assertions.assertTrue(exception.getMessage().equals("Corrupt Date: " + input));
    }

    @Test
    public void parseTaskFromString_unknownType_throwsPatrickException() {
        String input = "X | false | Ghost Task";

        PatrickException exception = Assertions.assertThrows(PatrickException.class,
                () -> Parser.parseTaskFromString(input));
        Assertions.assertTrue(exception.getMessage().equals("Unknown task type in " + input));
    }
}
