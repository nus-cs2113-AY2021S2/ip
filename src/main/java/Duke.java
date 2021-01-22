import java.util.Scanner;
import java.util.Vector;

public class Duke {
    public static final String LONG_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        // Initialize data
        Vector<Task> savedList = new Vector<>();

        greeting();

        Scanner in = new Scanner(System.in);
        Boolean exit = false;
        while (!exit) {
            String line = in.nextLine();
            String[] parameters = line.split(" ");
            switch(parameters[0]) {
            case "bye":
                exit = true;
                break;
            case "list":
                // Print out everything in the list, index starts from 1
                printIndent(LONG_LINE);
                for (int i = 0; i < savedList.size(); i += 1) {
                    Task task = savedList.get(i);
                    printIndent(String.format("%d.[%s] %s", i + 1, task.getStatusIcon(), task.getDescription()));
                }
                printIndent(LONG_LINE);
                break;
            case "done":
                if (parameters.length < 2) {
                    // An index must be provided for the task to be marked "done"
                    printLine("You will need to give me an index, like this: `done 2`");
                } else {
                    try {
                        int index = Integer.parseInt(parameters[1]);
                        if (index > savedList.size()) {
                            throw new IllegalArgumentException();
                        }

                        Task task = savedList.get(index - 1);
                        task.markAsDone();
                        savedList.set(index - 1, task);

                        printIndent(LONG_LINE);
                        printIndent("Nice! I've marked this task as done:");
                        printIndent(String.format("  [%s] %s", task.getStatusIcon(), task.getDescription()));
                        printIndent(LONG_LINE);
                    } catch (NumberFormatException e) {
                        printLine("Index provided is not a proper number.");
                    } catch (IllegalArgumentException e) {
                        printLine("Task with this index is not found in our database.");
                    }
                }
                break;
            default:
                // Save the line in the list
                savedList.add(new Task(line));
                printLine("added: " + line);
            }
        }
        in.close();

        goodbye();
    }

    public static void greeting() {
        printIndent(LONG_LINE);
        printIndent("Hello! I'm Duke");
        printIndent("What can I do for you?");
        printIndent(LONG_LINE);
    }

    public static void goodbye() {
        printLine("Bye. Hope to see you again soon!");
    }

    protected static void printLine(String line) {
        printIndent(LONG_LINE);
        printIndent(line);
        printIndent(LONG_LINE);
    }

    protected static void printIndent(String line) {
        System.out.println("    " + line);
    }
}
