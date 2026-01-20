import java.util.ArrayList;
import java.util.Scanner;

public class Gigachad {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        String horizontalString = "\t____________________________________________________________";
        String name = "Gigachad";
        System.out.println(String.format("%s\n\tHello! I'm %s.\n\tWhat can I do for you?\n%s",
                horizontalString, name, horizontalString));
        
        // Echo Loop
        String userInput = "";
        while (true) {
            System.out.print("\nEnter a command or task to add: ");
            userInput = scanner.nextLine();
            if (userInput.toLowerCase().equals("bye")) {
                break;
            } else if (userInput.toLowerCase().equals("list")) {
                int numTasks = tasks.size();
                if (numTasks == 0) {
                    System.out.println("There are no tasks!");
                } else {
                    System.out.println(horizontalString);
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < numTasks; i++) {
                        System.out.println(String.format("\t%d. %s", i + 1, tasks.get(i)));
                    }
                    System.out.println(horizontalString);
                }
            } else {
                tasks.add(new Task(userInput));
                System.out.println(String.format("%s\n\tadded: %s\n%s", horizontalString, userInput, horizontalString));
            }
        }
        
        System.out.println(String.format("%s\n\tBye. Hope to see you again soon!\n%s", horizontalString, horizontalString));
        scanner.close();
    }
}
