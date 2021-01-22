import java.util.Scanner;
import java.util.Vector;

public class Duke {
    public static final String LONG_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        // Initialize a vector to store all the tasks
        Vector<Task> tasks = new Vector<>();

        greeting();
        printIndent(LONG_LINE);

        Scanner in = new Scanner(System.in);
        Boolean exit = false;

        while (!exit) {
            String line = in.nextLine();
            String[] arguments = line.split(" ");

            printIndent(LONG_LINE);

            switch(arguments[0]) {
            case "bye":
                goodbye();
                exit = true;
                break;
            case "list":
                list(tasks);
                break;
            case "done":
                done(tasks, arguments);
                break;
            default:
                save(tasks, line);
            }

            printIndent(LONG_LINE);
        }
        in.close();
    }

    protected static void greeting() {
        printIndent("Hello! I'm Duke.");
        printIndent("What can I do for you?");
    }

    protected static void goodbye() {
        printIndent("Bye. Hope to see you again soon!");
    }

    // Print out everything in the list, index starts from 1
    protected static void list(Vector<Task> tasks) {
        for (int i = 0; i < tasks.size(); i += 1) {
            Task task = tasks.get(i);
            printIndent(String.format("%d.[%s] %s", i + 1, task.getStatusIcon(), task.getDescription()));
        }
    }

    // Mark a task to be done with index specified in arguments[1]
    protected static void done(Vector<Task> tasks, String[] arguments) {
        if (arguments.length < 2) {
            // An index must be provided for the task to be marked "done"
            printIndent("You will need to give me an index, like this: `done 2`.");
        } else {
            try {
                int index = Integer.parseInt(arguments[1]);
                if (index > tasks.size() || index < 1) {
                    // This index is out of the boundary of our database
                    throw new IllegalArgumentException();
                }

                Task task = tasks.get(index - 1);
                task.markAsDone();
                tasks.set(index - 1, task);

                printIndent("Nice! I've marked this task as done:");
                printIndent(String.format("  [%s] %s", task.getStatusIcon(), task.getDescription()));
            } catch (NumberFormatException e) {
                printIndent("Index provided is not a proper number.");
            } catch (IllegalArgumentException e) {
                printIndent("Task with this index is not found in our database.");
            }
        }
    }

    // Save a new task in the task list
    protected static void save(Vector<Task> tasks, String description) {
        tasks.add(new Task(description));
        printIndent("added: " + description);
    }

    // Print a line with 4 spaces as indentation
    protected static void printIndent(String line) {
        System.out.println("    " + line);
    }
}
