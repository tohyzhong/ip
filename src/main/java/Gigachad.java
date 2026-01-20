import java.util.ArrayList;
import java.util.Scanner;

public class Gigachad {
    public enum Command {
        BYE, LIST, ADD, MARK, UNMARK, TODO, DEADLINE, EVENT;

        public static Command getCommand(String userInput) {
            try {
                return Command.valueOf(userInput.split(" ")[0].toUpperCase());
            } catch (IllegalArgumentException err) {
                return ADD;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        String horizontalString = "\t____________________________________________________________";
        String name = "Gigachad";
        System.out.println(String.format("%s\n\tHello! I'm %s.\n\tWhat can I do for you?\n%s",
                horizontalString, name, horizontalString));

        // Echo Loop
        String userInput = "";
        String[] userInputArray;
        Task task;
        while (true) {
            System.out.print("\nEnter a command or task to add: ");
            userInput = scanner.nextLine();
            Command cmd = Command.getCommand(userInput);

            switch (cmd) {
                case BYE:
                    System.out.println(String.format("%s\n\tBye. Hope to see you again soon!\n%s",
                            horizontalString, horizontalString));
                    scanner.close();
                    return;

                case LIST:
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
                    break;

                case ADD:
                    tasks.add(new Task(userInput));
                    System.out.println(String.format("%s\n\tadded: %s\n%s",
                            horizontalString, userInput, horizontalString));
                    break;

                case MARK:
                    System.out.println(String.format("%s\n\t%s\n%s",
                            horizontalString,
                            tasks.get(Integer.parseInt(userInput.split(" ")[1]) - 1).setDone(),
                            horizontalString));
                    break;

                case UNMARK:
                    System.out.println(String.format("%s\n\t%s\n%s",
                            horizontalString,
                            tasks.get(Integer.parseInt(userInput.split(" ")[1]) - 1).setNotDone(),
                            horizontalString));
                    break;

                case TODO:
                    userInput = userInput.substring(5);
                    tasks.add(new ToDo(userInput));
                    task = tasks.get(tasks.size() - 1);
                    System.out.println(String.format("%s\n\tGot it. I've added this task: \n\t\t%s",
                            horizontalString, task.toString()));
                    System.out.println(String.format("\tNow you have %d tasks in the list.\n%s",
                            tasks.size(), horizontalString));
                    break;

                case DEADLINE:
                    userInput = userInput.substring(9);
                    userInputArray = userInput.split("\\s+/by\\s+");
                    tasks.add(new Deadline(userInputArray[0], userInputArray[1]));
                    task = tasks.get(tasks.size() - 1);
                    System.out.println(String.format("%s\n\tGot it. I've added this task: \n\t\t%s",
                            horizontalString, task.toString()));
                    System.out.println(String.format("\tNow you have %d tasks in the list.\n%s",
                            tasks.size(), horizontalString));
                    break;

                case EVENT:
                    userInput = userInput.substring(6);
                    userInputArray = userInput.split("\\s+/(from|to)\\s+");
                    tasks.add(new Event(userInputArray[0], userInputArray[1], userInputArray[2]));
                    task = tasks.get(tasks.size() - 1);
                    System.out.println(String.format("%s\n\tGot it. I've added this task: \n\t\t%s",
                            horizontalString, task.toString()));
                    System.out.println(String.format("\tNow you have %d tasks in the list.\n%s",
                            tasks.size(), horizontalString));
                    break;
            }
        }
    }
}
