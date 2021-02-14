package duke;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;
import duke.task.Task;

public class Duke {
    public static final String LONG_LINE = "------------------------------------------------------------";
    public static final String SAVE_PATH = "duke.save";

    public static void main(String[] args) {
        // Initialize a vector to store all the tasks
        Vector<Task> tasks = null;
        try {
            tasks = Helper.loadList(SAVE_PATH);
        } catch (Exception e) {
            Helper.printlnWithIndent("Got a problem when loading save file at " + SAVE_PATH + ": " + e.getMessage());
            Helper.printlnWithIndent("An empty list will be used instead!");
        } finally {
            if (tasks == null) {
                tasks = new Vector<>();
            }
        }

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
                    Helper.saveList(SAVE_PATH, tasks);
                    break;
                case "delete":
                    ActionHandler.deleteHandler(tasks, arguments);
                    break;
                case "deadline":
                    ActionHandler.deadlineHandler(tasks, arguments);
                    Helper.saveList(SAVE_PATH, tasks);
                    break;
                case "event":
                    ActionHandler.eventHandler(tasks, arguments);
                    Helper.saveList(SAVE_PATH, tasks);
                    break;
                case "todo":
                    ActionHandler.todoHandler(tasks, arguments);
                    Helper.saveList(SAVE_PATH, tasks);
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
