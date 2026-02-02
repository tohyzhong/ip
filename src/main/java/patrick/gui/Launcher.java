package patrick.gui;

import javafx.application.Application;

/**
 * A wrapper class to launch the Patrick application.
 * This class serves as the entry point to bypass JavaFX runtime checks that
 * require the main class to extend {@code javafx.application.Application},
 * ensuring the application can be packaged and executed correctly.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
