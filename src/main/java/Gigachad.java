import java.util.Scanner;

public class Gigachad {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String horizontalString = "\t____________________________________________________________";
        String name = "Gigachad";
        System.out.println(String.format("%s\n\tHello! I'm %s.\n\tWhat can I do for you?\n%s",
                horizontalString, name, horizontalString));
        
        // Echo Loop
        String userInput = "";
        while (true) {
            System.out.print("\nEnter a command (bye to exit): ");
            userInput = scanner.nextLine();
            if (userInput.toLowerCase().equals("bye")) {
                break;
            }

            System.out.println(String.format("%s\n\t%s\n%s", horizontalString, userInput, horizontalString));
        }
        
        System.out.println(String.format("%s\n\tBye. Hope to see you again soon!\n%s", horizontalString, horizontalString));
        scanner.close();
    }
}
