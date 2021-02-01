import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String LINE_DIVIDER = "\t____________________________________________________________";

    public static void main(String[] args) {
        printWelcomeMessage();
        interactWithUser();
        printExitMessage();
    }

    private static void printWelcomeMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t (Type 'help' for a list of commands)");
        System.out.println(LINE_DIVIDER);
    }

    private static void printExitMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println(LINE_DIVIDER);
    }

    private static void printHelpMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("\t List of valid commands:");
        System.out.println("\t bye - Exits program");
        System.out.println("\t list - Lists all tasks");
        System.out.println("\t done <task_number> - Mark the specified task as done");
        System.out.println("\t todo <task_description> - Create a new task with the specified description");
        System.out.println("\t deadline <task_description> /by <deadline_date> - " +
                "Create a new task with the specified description and deadline");
        System.out.println("\t event <task_description> /at <event_date> - " +
                "Create a new task with the specified description and event date");
        System.out.println(LINE_DIVIDER);
    }

    private static void listTasks(ArrayList<Task> tasks) {
        System.out.println(LINE_DIVIDER);
        if (tasks.size() == 0) {
            System.out.println("\t You have no tasks right now");
        } else {
            System.out.println("\t Here is a list of all your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t " + Integer.toString(i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(LINE_DIVIDER);
    }

    private static void addTodo(ArrayList<Task> tasks, String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        printTaskAdded(tasks, todo);
    }

    private static void addDeadline(ArrayList<Task> tasks, String description, String dueDate) {
        Deadline deadline = new Deadline(description, dueDate);
        tasks.add(deadline);
        printTaskAdded(tasks, deadline);
    }

    private static void addEvent(ArrayList<Task> tasks, String description, String eventDate) {
        Event event = new Event(description, eventDate);
        tasks.add(event);
        printTaskAdded(tasks, event);
    }

    private static void markTaskAsDone(ArrayList<Task> tasks, int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            printInvalidArgumentsMessage();
            return;
        }
        /* change from 1-based indexing to 0-based indexing */
        taskNumber = taskNumber - 1;
        tasks.get(taskNumber).markAsDone();
        System.out.println(LINE_DIVIDER);
        System.out.println("\t Nice! I've marked this task as done: ");
        System.out.println("\t   " + tasks.get(taskNumber));
        System.out.println(LINE_DIVIDER);
    }

    private static void printTaskAdded(ArrayList<Task> tasks, Task task) {
        System.out.println(LINE_DIVIDER);
        System.out.println("\t added: " + task);
        System.out.println(getNumTasksString(tasks.size()));
        System.out.println(LINE_DIVIDER);
    }

    private static void printFallbackMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("\t I didn't quite catch what you were saying. Please try again.");
        System.out.println("\t Try using \"help\" for a list of commands.");
        System.out.println(LINE_DIVIDER);
    }

    private static void printEmptyInputMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Please enter a command!");
        System.out.println(LINE_DIVIDER);
    }

    private static void printInsufficientArgumentsMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Please give me more details about the task!");
        System.out.println(LINE_DIVIDER);
    }

    private static void printInvalidArgumentsMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("That's an invalid task number!");
        System.out.println(LINE_DIVIDER);
    }

    private static void interactWithUser() {
        Scanner userInput = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        boolean isDoneReadingInputs = false;
        while (!isDoneReadingInputs) {
            String userCommand = userInput.nextLine();
            String[] commandTokens = tokenizeInput(userCommand);
            if (commandTokens.length == 0) {
                /* Handle empty inputs */
                printEmptyInputMessage();
                continue;
            }
            switch (commandTokens[0]) {
            case "bye":
                isDoneReadingInputs = true;
                break;
            case "list":
                listTasks(tasks);
                break;
            case "help":
                printHelpMessage();
                break;
            case "done":
                if (commandTokens.length < 2) {
                    printInsufficientArgumentsMessage();
                    break;
                }
                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(commandTokens[1]);
                } catch (NumberFormatException nfe) {
                    printInvalidArgumentsMessage();
                    break;
                }
                markTaskAsDone(tasks, taskNumber);
                break;
            case "todo":
                if (commandTokens.length < 2) {
                    printInsufficientArgumentsMessage();
                    break;
                }
                addTodo(tasks, commandTokens[1]);
                break;
            case "deadline":
                if (commandTokens.length < 3) {
                    printInsufficientArgumentsMessage();
                    break;
                }
                addDeadline(tasks, commandTokens[1], commandTokens[2]);
                break;
            case "event":
                if (commandTokens.length < 3) {
                    printInsufficientArgumentsMessage();
                    break;
                }
                addEvent(tasks, commandTokens[1], commandTokens[2]);
                break;
            default:
                /* Unknown command, prompt user to use the "help" command */
                printFallbackMessage();
                break;
            }
        }
    }

    private static String[] tokenizeInput(String userCommand) {
        String[] commandTokens = userCommand.split(" ", 2);
        if (commandTokens.length == 0) {
            /* Handle empty inputs */
            return commandTokens;
        }
        /* Make commands case-insensitive */
        commandTokens[0] = commandTokens[0].toLowerCase();
        String[] commandParsed = commandTokens;
        String[] commandArgs;
        switch (commandTokens[0]) {
        case "deadline":
            if (commandTokens.length > 1) {
                commandArgs = commandTokens[1].split("/by");
                commandParsed = new String[commandArgs.length + 1];
                commandParsed[0] = commandTokens[0];
                for (int i = 0; i < commandArgs.length; i++) {
                    commandParsed[i + 1] = commandArgs[i];
                }
            }
            break;
        case "event":
            if (commandTokens.length > 1) {
                commandArgs = commandTokens[1].split("/at");
                commandParsed = new String[commandArgs.length + 1];
                commandParsed[0] = commandTokens[0];
                for (int i = 0; i < commandArgs.length; i++) {
                    commandParsed[i + 1] = commandArgs[i];
                }
            }
            break;
        default:
            /**
             * For commands that do not require further tokenization
             * e.g. "todo", "list", "bye"
             */
            break;
        }
        /* Remove any trailing whitespace from the tokens */
        for (int i = 0; i < commandParsed.length; i++) {
            commandParsed[i] = commandParsed[i].trim();
        }
        return commandParsed;
    }

    private static String getNumTasksString(int numberOfTasks) {
        if (numberOfTasks == 1) return "\t You have " + Integer.toString(numberOfTasks) + " task in the list.";
        return "\t You have " + Integer.toString(numberOfTasks) + " tasks in the list.";
    }
}
