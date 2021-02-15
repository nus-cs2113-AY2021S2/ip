package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    private static final String LINE_DIVIDER = "\t____________________________________________________________";

    public static void main(String[] args) {
        DukePrinter.printWelcomeMessage();
        interactWithUser();
        DukePrinter.printExitMessage();
    }

    private static void interactWithUser() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        boolean isDoneReadingInputs = false;
        while (!isDoneReadingInputs) {
            ArrayList<String> commandTokens = DukeReader.readUserInput();
            try {
                isDoneReadingInputs = executeUserInput(tasks, commandTokens);
            } catch (DukeException dukeException) {
                String errorMessage = dukeException.getMessage();
                DukePrinter.printErrorMessage(errorMessage);
            } catch (NumberFormatException numberFormatException) {
                DukePrinter.printInvalidArgumentsMessage();
            }
        }
    }

    private static boolean executeUserInput(
            ArrayList<Task> tasks, ArrayList<String> commandTokens)
            throws DukeException, NumberFormatException {
        if (commandTokens.size() == 0) {
            throw new DukeException("Please enter a command.\n"
                    + "Try using \"help\" for a list of commands."
            );
        }
        boolean isDoneReadingInputs = false;
        switch (commandTokens.get(0)) {
        case DukeCommands.BYE_COMMAND:
            isDoneReadingInputs = true;
            break;
        case DukeCommands.LIST_COMMAND:
            DukePrinter.printTasks(tasks);
            break;
        case DukeCommands.HELP_COMMAND:
            DukePrinter.printHelpMessage();
            break;
        case DukeCommands.DONE_COMMAND:
            markTaskAsDone(tasks, commandTokens);
            break;
        case DukeCommands.TODO_COMMAND:
            addTodo(tasks, commandTokens);
            break;
        case DukeCommands.DEADLINE_COMMAND:
            addDeadline(tasks, commandTokens);
            break;
        case DukeCommands.EVENT_COMMAND:
            addEvent(tasks, commandTokens);
            break;
        case DukeCommands.DELETE_COMMAND:
            deleteTask(tasks, commandTokens);
            break;
        default:
            /* Unknown command, prompt user to use the "help" command */
            DukePrinter.printFallbackMessage();
            break;
        }
        return isDoneReadingInputs;
    }

    private static void addTodo(ArrayList<Task> tasks, ArrayList<String> commandArguments) throws DukeException {
        if (commandArguments.size() < 2) {
            throw new DukeException("Please give me more details about the task!");
        }
        if (commandArguments.get(1).equals("")) {
            throw new DukeException("The description of a task can't be empty. Please try again.");
        }
        String description = commandArguments.get(1);
        Todo todo = new Todo(description);
        tasks.add(todo);
        DukePrinter.printTaskAdded(tasks, todo);
    }

    private static void addDeadline(ArrayList<Task> tasks, ArrayList<String> commandArguments) throws DukeException {
        if (commandArguments.size() < 3) {
            throw new DukeException("Please give me more details about the task!");
        }
        if (commandArguments.get(1).equals("")) {
            throw new DukeException("The description of a task can't be empty. Please try again.");
        }
        if (commandArguments.get(2).equals("")) {
            throw new DukeException("Please specify a deadline for the task.");
        }
        String description = commandArguments.get(1);
        String dueDate = commandArguments.get(2);
        Deadline deadline = new Deadline(description, dueDate);
        tasks.add(deadline);
        DukePrinter.printTaskAdded(tasks, deadline);
    }

    private static void addEvent(ArrayList<Task> tasks, ArrayList<String> commandArguments) throws DukeException {
        if (commandArguments.size() < 3) {
            throw new DukeException("Please give me more details about the task!");
        }
        if (commandArguments.get(1).equals("")) {
            throw new DukeException("The description of a task can't be empty. Please try again.");
        }
        if (commandArguments.get(2).equals("")) {
            throw new DukeException("Please specify a date for the event.");
        }
        String description = commandArguments.get(1);
        String eventDate = commandArguments.get(2);
        Event event = new Event(description, eventDate);
        tasks.add(event);
        DukePrinter.printTaskAdded(tasks, event);
    }

    private static void markTaskAsDone(ArrayList<Task> tasks, ArrayList<String> commandArguments)
            throws DukeException, NumberFormatException {
        if (commandArguments.size() < 2) {
            throw new DukeException("Please give me more details about the task!");
        }
        int taskNumber = Integer.parseInt(commandArguments.get(1));
        /* Enforce that taskNumber is valid */
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("That's an invalid task number!");
        }
        /* Change from 1-based indexing to 0-based indexing */
        taskNumber = taskNumber - 1;
        tasks.get(taskNumber).markAsDone();
        DukePrinter.printTaskMarkedDone(tasks.get(taskNumber));
    }

    private static void deleteTask(ArrayList<Task> tasks, ArrayList<String> commandArguments) throws DukeException {
        if (commandArguments.size() < 2) {
            throw new DukeException("Please give me more details about the task!");
        }
        int taskNumber = Integer.parseInt(commandArguments.get(1));
        /* Enforce that taskNumber is valid */
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("That's an invalid task number!");
        }
        /* Change from 1-based indexing to 0-based indexing */
        taskNumber = taskNumber - 1;
        DukePrinter.printTaskDeleted(tasks.get(taskNumber), tasks.size() - 1);
        tasks.remove(taskNumber);
    }
}
