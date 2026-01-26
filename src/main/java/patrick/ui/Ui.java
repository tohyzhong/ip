package patrick.ui;

import java.util.Scanner;

import patrick.Patrick;

public class Ui {
    private static final String HORIZONTAL_LINE = "\t____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void display(String str) {
        System.out.println(Ui.HORIZONTAL_LINE);
        str = str.replace("\n", "\n\t");
        System.out.println("\t" + str);
        System.out.println(Ui.HORIZONTAL_LINE);
    }

    public void displayWelcomeMessage() {
        this.display(String.format("Hello! I'm %s.\nWhat can I do for you?", Patrick.BOT_NAME));
    }

    public void endUi() {
        this.display("Bye. Hope to see you again soon!");
        this.scanner.close();
    }

    public String readInput() {
        System.out.print("\nEnter a command or task to add: ");
        String userInput = this.scanner.nextLine();
        System.out.println();

        return userInput;
    }

    public void displayError(String error) {
        this.display("ERROR! " + error);
    }

    public void displayParamError(String error) {
        this.display("Parameter Failure! " + error);
    }
}
