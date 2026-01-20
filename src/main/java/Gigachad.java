import java.util.ArrayList;
import java.util.Scanner;

public class Gigachad {
    public enum Command {
        BYE, LIST, ERROR, MARK, UNMARK, TODO, DEADLINE, EVENT;

        public static Command getCommand(String userInput) {
            try {
                return Command.valueOf(userInput.split(" ")[0].toUpperCase());
            } catch (IllegalArgumentException err) {
                return ERROR;
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

                case ERROR:
                    System.out.println(String.format("%s\n\t%s\n%s",
                            horizontalString,
                            "Unknown command. Please try again.",
                            horizontalString));
                    break;

                case MARK:
                    userInputArray = userInput.split(" ");
                    if (userInputArray.length < 2) {
                        System.out.println(String.format("%s\n\t%s\n%s",
                                horizontalString,
                                "Error: Please enter a task number.",
                                horizontalString));
                        break;
                    } else if (tasks.size() < 1) {
                        System.out.println(String.format("%s\n\t%s\n%s",
                                horizontalString,
                                "Error: There are no tasks.",
                                horizontalString));
                        break;
                    }

                    try {
                        System.out.println(String.format("%s\n\t%s\n%s",
                                horizontalString,
                                tasks.get(Integer.parseInt(userInputArray[1]) - 1).setDone(),
                                horizontalString));
                    } catch (NumberFormatException err) {
                        System.out.println(String.format("%s\n\t%s\n%s",
                                horizontalString,
                                "Error: Please enter a valid task number.",
                                horizontalString));
                    } catch (IndexOutOfBoundsException err) {
                        System.out.println(String.format("%s\n\t%s %d (inclusive).\n%s",
                                horizontalString,
                                "Error: Please enter a valid task number between 1 and",
                                tasks.size(),
                                horizontalString));
                    }

                    break;

                case UNMARK:
                    userInputArray = userInput.split(" ");
                    if (userInputArray.length < 2) {
                        System.out.println(String.format("%s\n\t%s\n%s",
                                horizontalString,
                                "Error: Please enter a task number.",
                                horizontalString));
                        break;
                    } else if (tasks.size() < 1) {
                        System.out.println(String.format("%s\n\t%s\n%s",
                                horizontalString,
                                "Error: There are no tasks.",
                                horizontalString));
                        break;
                    }

                    try {
                        System.out.println(String.format("%s\n\t%s\n%s",
                                horizontalString,
                                tasks.get(Integer.parseInt(userInputArray[1]) - 1).setNotDone(),
                                horizontalString));
                    } catch (NumberFormatException err) {
                        System.out.println(String.format("%s\n\t%s\n%s",
                                horizontalString,
                                "Error: Please enter a valid task number.",
                                horizontalString));
                    } catch (IndexOutOfBoundsException err) {
                        System.out.println(String.format("%s\n\t%s %d (inclusive).\n%s",
                                horizontalString,
                                "Error: Please enter a valid task number between 1 and",
                                tasks.size(),
                                horizontalString));
                    }
                    break;

                case TODO:
                    if (userInput.length() <= 5) {
                        System.out.println(String.format("%s\n\t%s\n%s",
                                horizontalString,
                                "Error: Please enter a task name.",
                                horizontalString));
                        break;
                    }

                    userInput = userInput.substring(5);
                    tasks.add(new ToDo(userInput));
                    task = tasks.get(tasks.size() - 1);
                    System.out.println(String.format("%s\n\tGot it. I've added this task: \n\t\t%s",
                            horizontalString, task.toString()));
                    System.out.println(String.format("\tNow you have %d tasks in the list.\n%s",
                            tasks.size(), horizontalString));
                    break;

                case DEADLINE:
                    if (userInput.length() <= 9) {
                        System.out.println(String.format("%s\n\t%s\n%s",
                                horizontalString,
                                "Error: Please enter a task name.",
                                horizontalString));
                        break;
                    }

                    userInput = userInput.substring(9);
                    userInputArray = userInput.split("\\s+/by\\s+");

                    if (userInputArray.length < 2) {
                        System.out.println(String.format("%s\n\t%s\n%s",
                                horizontalString,
                                "Error: Command parameters are missing. Do you have /by ?",
                                horizontalString));
                        break;
                    }

                    tasks.add(new Deadline(userInputArray[0], userInputArray[1]));
                    task = tasks.get(tasks.size() - 1);
                    System.out.println(String.format("%s\n\tGot it. I've added this task: \n\t\t%s",
                            horizontalString, task.toString()));
                    System.out.println(String.format("\tNow you have %d tasks in the list.\n%s",
                            tasks.size(), horizontalString));
                    break;

                case EVENT:
                    if (userInput.length() <= 6) {
                        System.out.println(String.format("%s\n\t%s\n%s",
                                horizontalString,
                                "Error: Please enter a task name.",
                                horizontalString));
                        break;
                    }

                    userInput = userInput.substring(6);
                    userInputArray = userInput.split("\\s+/(from|to)\\s+");

                    if (userInputArray.length < 3) {
                        System.out.println(String.format("%s\n\t%s\n%s",
                                horizontalString,
                                "Error: Command parameters are missing. Do you have /from and /to ?",
                                horizontalString));
                        break;
                    }

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
