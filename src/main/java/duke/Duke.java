package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class Duke {

    private static final String LINE_DIVIDER = "\t____________________________________________________________";


    public static void main(String[] args) {
        DukePrinter.printWelcomeMessage();
        interactWithUser();
        DukePrinter.printExitMessage();
    }

    private static void addTodo(ArrayList<Task> tasks, String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        DukePrinter.printTaskAdded(tasks, todo);
    }

    private static void addDeadline(ArrayList<Task> tasks, String description, String dueDate) {
        Deadline deadline = new Deadline(description, dueDate);
        tasks.add(deadline);
        DukePrinter.printTaskAdded(tasks, deadline);
    }

    private static void addEvent(ArrayList<Task> tasks, String description, String eventDate) {
        Event event = new Event(description, eventDate);
        tasks.add(event);
        DukePrinter.printTaskAdded(tasks, event);
    }

    private static void markTaskAsDone(ArrayList<Task> tasks, int taskNumber) {
        /* Enforce that taskNumber is valid */
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            DukePrinter.printInvalidArgumentsMessage();
            return;
        }
        /* Change from 1-based indexing to 0-based indexing */
        taskNumber = taskNumber - 1;
        tasks.get(taskNumber).markAsDone();
        System.out.println(LINE_DIVIDER);
        System.out.println("\t Nice! I've marked this task as done: ");
        System.out.println("\t   " + tasks.get(taskNumber));
        System.out.println(LINE_DIVIDER);
    }

    private static void interactWithUser() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        boolean isDoneReadingInputs = false;
        while (!isDoneReadingInputs) {
            String[] commandTokens = DukeReader.readUserInput();
            try {
                isDoneReadingInputs = parseCommandTokens(tasks, isDoneReadingInputs, commandTokens);
            } catch (DukeException dukeException) {
                String errorMessage = dukeException.getMessage();
                DukePrinter.printErrorMessage(errorMessage);
            } catch (NumberFormatException numberFormatException) {
                DukePrinter.printInvalidArgumentsMessage();
            }
        }
    }

    private static boolean parseCommandTokens(
            ArrayList<Task> tasks, boolean isDoneReadingInputs, String[] commandTokens)
            throws DukeException, NumberFormatException {
        if (commandTokens.length == 0) {
            throw new DukeException(
                    "Please enter a command.\n" +
                    "Try using \"help\" for a list of commands."
            );
        }
        switch (commandTokens[0]) {
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
            if (commandTokens.length < 2) {
                throw new DukeException(
                        "Please give me more details about the task!"
                );
            }
            int taskNumber = Integer.parseInt(commandTokens[1]);
            markTaskAsDone(tasks, taskNumber);
            break;
        case DukeCommands.TODO_COMMAND:
            if (commandTokens.length < 2) {
                throw new DukeException(
                        "Please give me more details about the task!"
                );
            }
            if (commandTokens[1].equals("")) {
                throw new DukeException(
                        "The description of a task can't be empty. Please try again."
                );
            }
            addTodo(tasks, commandTokens[1]);
            break;
        case DukeCommands.DEADLINE_COMMAND:
            if (commandTokens.length < 3) {
                throw new DukeException(
                        "Please give me more details about the task!"
                );
            }
            if (commandTokens[1].equals("")) {
                throw new DukeException(
                        "The description of a task can't be empty. Please try again."
                );
            }
            if (commandTokens[2].equals("")) {
                throw new DukeException(
                        "Please specify a deadline for the task."
                );
            }
            addDeadline(tasks, commandTokens[1], commandTokens[2]);
            break;
        case DukeCommands.EVENT_COMMAND:
            if (commandTokens.length < 3) {
                throw new DukeException(
                        "Please give me more details about the task!"
                );
            }
            if (commandTokens[1].equals("")) {
                throw new DukeException(
                        "The description of a task can't be empty. Please try again."
                );
            }
            if (commandTokens[2].equals("")) {
                throw new DukeException(
                        "Please specify a date for the event."
                );
            }
            addEvent(tasks, commandTokens[1], commandTokens[2]);
            break;
        default:
            /* Unknown command, prompt user to use the "help" command */
            DukePrinter.printFallbackMessage();
            break;
        }
        return isDoneReadingInputs;
    }

}
