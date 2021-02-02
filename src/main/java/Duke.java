import java.util.Scanner;

public class Duke {

    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_BYE_WORD = "bye";
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_DEADLINE_BY_TOKEN = "/by";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_EVENT_AT_TOKEN = "/at";
    private static final String COMMAND_DONE_WORD = "done";

    private static final String DOUBLE_SPACE_PREFIX_STRING_FORMAT = "  %s";
    private static final String HORIZONTAL_LINE = "_".repeat(60);
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\n"
            + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String LIST_TASK_MESSAGE = "Here are the tasks in your list:";
    private static final String LIST_NO_TASK_MESSAGE = "No task in record.";
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String TASK_TOTAL_TASKS_STRING_FORMAT = "Now you have %d tasks in the list.";
    private static final String TASK_MARK_AS_DONE_FORMAT = "Nice! I've marked this task as done:";
    private static final String ERROR_COMMAND_MESSAGE = "Invalid command.";
    private static final String ERROR_INVALID_DEADLINE_MESSAGE = "Invalid deadline.";
    private static final String ERROR_INVALID_EVENT_MESSAGE = "Invalid event.";
    private static final String ERROR_PREFIX_MESSAGE = "ERROR: ";
    private static final String ERROR_INVALID_TASK_NAME_MESSAGE = "Invalid task name.";
    private static final String ERROR_INVALID_TASK_NUMBER_MESSAGE = "Invalid task number.";


    public static Task[] tasks = new Task[100];
    public static int numberOfTasks = 0;
    private static final Scanner SCANNER = new Scanner(System.in);

    private static void recordTodo(String commandArgs) {
        String taskDescription = parseArgument(commandArgs, null);
        if (taskDescription == null || taskDescription.length() == 0) {
            printError(ERROR_INVALID_TASK_NAME_MESSAGE);
            return;
        }
        recordTask(new Todo(taskDescription));
    }

    private static void recordDeadline(String commandArgs) {
        String taskDescription = parseArgument(commandArgs, null);
        if (taskDescription == null || taskDescription.length() == 0) {
            printError(ERROR_INVALID_TASK_NAME_MESSAGE);
            return;
        }
        String deadlineBy = parseArgument(commandArgs, COMMAND_DEADLINE_BY_TOKEN);
        if (deadlineBy == null || deadlineBy.length() == 0) {
            printError(ERROR_INVALID_DEADLINE_MESSAGE);
            return;
        }
        recordTask(new Deadline(taskDescription, deadlineBy));
    }

    private static void recordEvent(String commandArgs) {
        String taskDescription = parseArgument(commandArgs, null);
        if (taskDescription == null || taskDescription.length() == 0) {
            printError(ERROR_INVALID_TASK_NAME_MESSAGE);
            return;
        }
        String eventAt = parseArgument(commandArgs, COMMAND_EVENT_AT_TOKEN);
        if (eventAt == null || eventAt.length() == 0) {
            printError(ERROR_INVALID_EVENT_MESSAGE);
            return;
        }
        recordTask(new Event(taskDescription, eventAt));
    }

    private static void recordTask(Task task) {
        tasks[numberOfTasks] = task;
        numberOfTasks++;
        printStatements(TASK_ADDED_MESSAGE,
                String.format(DOUBLE_SPACE_PREFIX_STRING_FORMAT, task),
                String.format(TASK_TOTAL_TASKS_STRING_FORMAT, numberOfTasks));
    }

    private static void listTasks() {
        if (numberOfTasks == 0) {
            printStatements(LIST_NO_TASK_MESSAGE);
        } else {
            printHorizontalLine();
            printStatement(LIST_TASK_MESSAGE);
            for (int i = 0; i < numberOfTasks; i++) {
                Task task = tasks[i];
                System.out.printf("\t %d.%s", i + 1, task);
                System.out.println();
            }
            printHorizontalLine();
        }
    }

    private static void markTaskDone(String commandArgs) {
        try {
            String argValue = parseArgument(commandArgs, null);
            if (argValue == null) {
                throw new Exception(ERROR_INVALID_TASK_NUMBER_MESSAGE);
            }
            int taskNumber = Integer.parseInt(argValue);
            if (taskNumber > 0 && taskNumber <= numberOfTasks) {
                tasks[taskNumber - 1].isDone = true;
                Task task = tasks[taskNumber - 1];
                printStatements(TASK_MARK_AS_DONE_FORMAT,
                        String.format(DOUBLE_SPACE_PREFIX_STRING_FORMAT, task));
            } else {
                throw new Exception(ERROR_INVALID_TASK_NUMBER_MESSAGE);
            }
        } catch (Exception e) {
            printError(ERROR_INVALID_TASK_NUMBER_MESSAGE);
        }
    }

    private static void printHorizontalLine() {
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    private static void printStatement(String statement) {
        String[] fragments = statement.split("\\R");
        for (String fragment : fragments) {
            System.out.println("\t " + fragment);
        }
    }

    private static void printStatements(String... statement) {
        printHorizontalLine();
        for (String s : statement) {
            printStatement(s);
        }
        printHorizontalLine();
    }

    private static void printError(String errorDescription) {
        printHorizontalLine();
        printStatement(String.format("%s %s", ERROR_PREFIX_MESSAGE, errorDescription));
        printHorizontalLine();
    }

    private static void printWelcomeMessage() {
        printStatements(LOGO, WELCOME_MESSAGE);
    }

    private static void exitProgram() {
        printStatements(EXIT_MESSAGE);
        System.exit(0);
    }

    private static void executeCommand(String userInput) {
        String[] parsedCommand = parseCommand(userInput);
        String commandName = parsedCommand[0];
        String commandArgs = parsedCommand[1];

        switch (commandName) {
        case COMMAND_LIST_WORD:
            listTasks();
            break;
        case COMMAND_TODO_WORD:
            recordTodo(commandArgs);
            break;
        case COMMAND_DEADLINE_WORD:
            recordDeadline(commandArgs);
            break;
        case COMMAND_EVENT_WORD:
            recordEvent(commandArgs);
            break;
        case COMMAND_DONE_WORD:
            markTaskDone(commandArgs);
            break;
        case COMMAND_BYE_WORD:
            exitProgram();
        default:
            printError(ERROR_COMMAND_MESSAGE);
        }
    }

    private static String[] parseCommand(String userInput) {
        final String[] parsedCommand = userInput.trim().split("\\s+", 2);
        // parsedCommand's length after split will always be more than zero.
        if (parsedCommand.length == 1) {
            return new String[] {parsedCommand[0], ""};
        }
        return parsedCommand;
    }

    private static String parseArgument(String commandArgs, String token) {
        int readFromIndex = 0;
        int readUntilIndex = commandArgs.length();
        if (token != null) {
            if (commandArgs.contains(token)) {
                readFromIndex = commandArgs.indexOf(token) + token.length();
            } else {
                return null;
            }
        }
        int result = commandArgs.indexOf("/", readFromIndex);
        if (result != -1) {
            readUntilIndex = result;
        }
        return commandArgs.substring(readFromIndex, readUntilIndex).trim();
    }

    private static String getUserInput() {
        String userInput = SCANNER.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = SCANNER.nextLine();
        }
        return userInput;
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        while (true) {
            String userCommand = getUserInput();
            executeCommand(userCommand);
        }
    }
}
