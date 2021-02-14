package duke;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;
import duke.task.Task;

public class Duke {
    public static final String LONG_LINE = "------------------------------------------------------------";

    public static void main(String[] args) {
        // Initialize a vector to store all the tasks
        Vector<Task> tasks = new Vector<>();

        ActionHandler.greetingHandler();
        Helper.printlnWithIndent(LONG_LINE);

        Scanner in = new Scanner(System.in);
        Boolean isExit = false;

        while (!isExit) {
            String line = in.nextLine();
            // Split the line by any whitespaces characters (including spaces, tabs etc.)
            String[] arguments = line.split("\\s+");
            // If first argument (command) is empty, there are empty spaces typed in at the front - so we remove it
            if (arguments[0].isEmpty()) {
                arguments = Arrays.copyOfRange(arguments, 1, arguments.length);
            }

            Helper.printlnWithIndent(LONG_LINE);

            try {
                switch(arguments[0]) {
                case "bye":
                    ActionHandler.byeHandler();
                    isExit = true;
                    break;
                case "list":
                    ActionHandler.listHandler(tasks);
                    break;
                case "done":
                    ActionHandler.doneHandler(tasks, arguments);
                    break;
                case "delete":
                    ActionHandler.deleteHandler(tasks, arguments);
                    break;
                case "deadline":
                    ActionHandler.deadlineHandler(tasks, arguments);
                    break;
                case "event":
                    ActionHandler.eventHandler(tasks, arguments);
                    break;
                case "todo":
                    ActionHandler.todoHandler(tasks, arguments);
                    break;
                default:
                    throw new InvalidInputException(InputExceptionType.UNKNOWN_COMMAND);
                }
            } catch (Exception e) {
                Helper.printlnWithIndent("Oops! " + e.getMessage());
            }

            Helper.printlnWithIndent(LONG_LINE);
        }
        in.close();
    }
}
