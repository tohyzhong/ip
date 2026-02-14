package patrick.command;

import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import patrick.exception.InvalidParameterException;
import patrick.exception.PatrickException;
import patrick.gui.MainWindow;
import patrick.storage.Storage;
import patrick.task.TaskList;

/**
 * Test suite for EventCommand.
 */
public class EventCommandTest {

    @TempDir
    Path tempDir;

    private TaskList tasks;
    private MainWindow gui;
    private Storage storage;

    @BeforeEach
    public void setUp() throws PatrickException {
        this.tasks = new TaskList();
        this.gui = new TestMainWindow();
        this.storage = new TestStorage(this.tempDir.resolve("dummy.txt").toString());
    }

    @Test
    public void execute_validInput_addsEvent() throws PatrickException {
        String userInput = "event Test /from 2026-01-01 /to 2026-01-02";
        EventCommand.execute(this.tasks, this.gui, userInput, this.storage);
        Assertions.assertEquals(1, this.tasks.getSize());
        Assertions.assertTrue(this.tasks.getTask(0).toString().contains("Test"));
    }

    @Test
    public void execute_missingFrom_throwsException() {
        String userInput = "event Test /to 2026-01-02";
        Assertions.assertThrows(InvalidParameterException.class, () -> {
            EventCommand.execute(this.tasks, this.gui, userInput, this.storage);
        });
    }

    @Test
    public void execute_missingTo_throwsException() {
        String userInput = "event Test /from 2026-01-01";
        Assertions.assertThrows(InvalidParameterException.class, () -> {
            EventCommand.execute(this.tasks, this.gui, userInput, this.storage);
        });
    }

    @Test
    public void execute_invalidDate_throwsException() {
        String userInput = "event Test /from invalid /to 2026-01-02";
        Assertions.assertThrows(InvalidParameterException.class, () -> {
            EventCommand.execute(this.tasks, this.gui, userInput, this.storage);
        });
    }

    // Dummy implementations for testing
    private static class TestMainWindow extends MainWindow {
        @Override
        public void display(String message) {
            // Do nothing
        }
    }

    private static class TestStorage extends Storage {
        public TestStorage(String path) throws PatrickException {
            super(path);
        }

        @Override
        public void save(TaskList tasks) throws PatrickException {
            // Do nothing
        }
    }
}
