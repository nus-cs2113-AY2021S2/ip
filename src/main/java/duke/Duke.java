package duke;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Duke {
    public static final String LONG_LINE = "------------------------------------------------------------";

    public static void main(String[] args) {
        // Initialize a vector to store all the tasks
        Vector<Task> tasks = new Vector<>();

        greetingHandler();
        printlnWithIndent(LONG_LINE);

        Scanner in = new Scanner(System.in);
        Boolean isExit = false;

        while (!isExit) {
            String line = in.nextLine();
            String[] arguments = line.split(" ");

            printlnWithIndent(LONG_LINE);

            switch(arguments[0]) {
            case "bye":
                byeHandler();
                isExit = true;
                break;
            case "list":
                listHandler(tasks);
                break;
            case "done":
                doneHandler(tasks, arguments);
                break;
            case "deadline":
                deadlineHandler(tasks, arguments);
                break;
            case "event":
                eventHandler(tasks, arguments);
                break;
            case "todo":
                todoHandler(tasks, arguments);
                break;
            default:
                invalidCommandHandler(tasks, line);
            }

            printlnWithIndent(LONG_LINE);
        }
        in.close();
    }

    /*
     * Handlers for different actions
     */

    // Print a greeting message when the program is invoked
    protected static void greetingHandler() {
        printlnWithIndent("Hello! I'm Duke.");
        printlnWithIndent("What can I do for you?");
    }

    // Print a goodbye message before the program exits
    protected static void byeHandler() {
        printlnWithIndent("Bye. Hope to see you again soon!");
    }

    // Print out everything in the list, index starts from 1
    protected static void listHandler(Vector<Task> tasks) {
        printlnWithIndent("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1) {
            printlnWithIndent(String.format("%d.\t%s", i + 1, tasks.get(i)));
        }
    }

    // Mark a task to be done with index specified in arguments[1]
    protected static void doneHandler(Vector<Task> tasks, String[] arguments) {
        if (arguments.length < 2) {
            // An index must be provided for the task to be marked "done"
            printlnWithIndent("You will need to give me an index, like this: `done 2`.");
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

                printlnWithIndent("Nice! I've marked this task as done:");
                printlnWithIndent("\t" + task);
            } catch (NumberFormatException e) {
                printlnWithIndent("Index provided is not a proper number.");
            } catch (IllegalArgumentException e) {
                printlnWithIndent("Task with this index is not found in our database.");
            }
        }
    }

    // Create a deadline task
    protected static void deadlineHandler(Vector<Task> tasks, String[] arguments) {
        int i = findIndex(arguments, "/by");
        if (i != -1) {
            String description = String.join(" ", Arrays.copyOfRange(arguments, 1, i));
            String by = String.join(" ", Arrays.copyOfRange(arguments, i + 1, arguments.length));
            tasks.add(new Deadline(description, by));
            printNewTask(tasks);
        } else {
            printlnWithIndent("You must specify a deadline after /by");
        }
    }

    // Create an event task
    protected static void eventHandler(Vector<Task> tasks, String[] arguments) {
        int i = findIndex(arguments, "/at");
        if (i != -1) {
            String description = String.join(" ", Arrays.copyOfRange(arguments, 1, i));
            String at = String.join(" ", Arrays.copyOfRange(arguments, i + 1, arguments.length));
            tasks.add(new Event(description, at));
            printNewTask(tasks);
        } else {
            printlnWithIndent("You must specify a event time after /at");
        }
    }

    // Create a todo task
    protected static void todoHandler(Vector<Task> tasks, String[] arguments) {
        String description = String.join(" ", Arrays.copyOfRange(arguments, 1, arguments.length));
        tasks.add(new ToDo(description));
        printNewTask(tasks);
    }

    // Save a new task with no specific type in the task list
    protected static void invalidCommandHandler(Vector<Task> tasks, String description) {
        printlnWithIndent("I cannot recognize what your command means.");
    }

    /*
     * Help functions
     */

    // Print a line with 1 tab as indentation
    protected static void printlnWithIndent(String line) {
        System.out.println("\t" + line);
    }

    // Print a message for a successful insertion of task
    protected static void printNewTask(Vector<Task> tasks) {
        int size = tasks.size();
        printlnWithIndent("Great. We added a new task:");
        printlnWithIndent("\t" + tasks.get(size - 1));
        printlnWithIndent(String.format("You have in total %d tasks", size));
    }

    // Find the index of a string in a string array
    // Return the index if found and -1 if not found
    protected static int findIndex(String[] haystack, String needle) {
        for (int i = 0; i < haystack.length; i += 1) {
            if (haystack[i].equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
