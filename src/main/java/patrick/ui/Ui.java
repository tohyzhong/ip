package patrick.ui;

import java.util.Scanner;

import patrick.Patrick;

/**
 * Handles the user interface of Patrick.
 * This class is responsible for reading user input from the console and
 * displaying formatted messages, errors, and welcome and farewell greetings.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "\t____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a formatted message wrapped in horizontal lines.
     * This method indents multi-line strings to maintain consistent formatting.
     * 
     * @param str The message string to be displayed.
     */
    public void display(String str) {
        System.out.println(Ui.HORIZONTAL_LINE);
        str = str.replace("\n", "\n\t");
        System.out.println("\t" + str);
        System.out.println(Ui.HORIZONTAL_LINE);
    }

    /**
     * Displays the initial welcome message when the bot starts.
     */
    public void displayWelcomeMessage() {
        this.display(String.format("Hello! I'm %s.\nWhat can I do for you?", Patrick.BOT_NAME));
    }

    /**
     * Displays the farewell greeting and closes the scanner.
     */
    public void endUi() {
        this.display("Bye. Hope to see you again soon!");
        this.scanner.close();
    }

    /**
     * Reads a line of text entered by the user.
     * 
     * @return The raw string input provided by the user.
     */
    public String readInput() {
        System.out.print("\nEnter a command or task to add: ");
        String userInput = this.scanner.nextLine();
        System.out.println();

        return userInput;
    }

    /**
     * Displays a general error message to the user.
     * 
     * @param error The error message.
     */
    public void displayError(String error) {
        this.display("ERROR! " + error);
    }

    /**
     * Displays a specific error related to command parameters.
     * This display function specifies the type of error to alert the user.
     * 
     * @param error The parameter error detail message.
     */
    public void displayParamError(String error) {
        this.display("Parameter Failure! " + error);
    }
}
