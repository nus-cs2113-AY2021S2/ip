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

    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String LIST_TASK_MESSAGE = "Here are the tasks in your list:";
    private static final String LIST_NO_TASK_MESSAGE = "No task in record.";
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String TASK_TOTAL_TASKS_STRING_FORMAT = "Now you have %d tasks in the list.";
    private static final String TASK_MARK_AS_DONE_FORMAT = "Nice! I've marked this task as done:";
    private static final String ERROR_COMMAND_MESSAGE = "Invalid command.";
    private static final String ERROR_INVALID_DEADLINE_MESSAGE = "Invalid deadline.";
    private static final String ERROR_INVALID_EVENT_MESSAGE = "Invalid event.";
    private static final String ERROR_INVALID_TASK_NAME_MESSAGE = "Invalid task name.";
    private static final String ERROR_INVALID_TASK_NUMBER_MESSAGE = "Invalid task number.";

    private static final int MAX_NUMBER_OF_TASKS = 100;
    private static Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];
    private static int numberOfTasks = 0;


    /**
     * Records a new Todo task into the global task array.
     * Ensures the task description is given in {@code commandArgs}
     * Fails if task description argument value is invalid.
     *
     * @param commandArgs this should contain task description
     */
    private static void recordTodo(String commandArgs) {
        String taskDescription = parseArgument(commandArgs, null);
        if (isArgumentValueEmpty(taskDescription)) {
            TextUI.printError(ERROR_INVALID_TASK_NAME_MESSAGE);
            return;
        }
        recordTask(new Todo(taskDescription));
    }

    /**
     * Records a new Deadline task into the global task array.
     * Ensures the task description and deadline-by is given in {@code commandArgs}
     * Fails if any argument value is invalid.
     *
     * @param commandArgs this should contain task description and deadline-by
     */
    private static void recordDeadline(String commandArgs) {
        String taskDescription = parseArgument(commandArgs, null);
        if (isArgumentValueEmpty(taskDescription)) {
            TextUI.printError(ERROR_INVALID_TASK_NAME_MESSAGE);
            return;
        }

        String deadlineBy = parseArgument(commandArgs, COMMAND_DEADLINE_BY_TOKEN);
        if (isArgumentValueEmpty(deadlineBy)) {
            TextUI.printError(ERROR_INVALID_DEADLINE_MESSAGE);
            return;
        }
        recordTask(new Deadline(taskDescription, deadlineBy));
    }

    /**
     * Records a new Event task into the global task array.
     * Ensures the task description and event-at is given in {@code commandArgs}
     * Fails if any argument value is invalid.
     *
     * @param commandArgs this should contain task description and event-at
     */
    private static void recordEvent(String commandArgs) {
        String taskDescription = parseArgument(commandArgs, null);
        if (isArgumentValueEmpty(taskDescription)) {
            TextUI.printError(ERROR_INVALID_TASK_NAME_MESSAGE);
            return;
        }

        String eventAt = parseArgument(commandArgs, COMMAND_EVENT_AT_TOKEN);
        if (isArgumentValueEmpty(eventAt)) {
            TextUI.printError(ERROR_INVALID_EVENT_MESSAGE);
            return;
        }
        recordTask(new Event(taskDescription, eventAt));
    }

    /**
     * Records the given task into the global task array.
     * Increments the global task count.
     *
     * @param task task object to be recorded
     */
    private static void recordTask(Task task) {
        tasks[numberOfTasks] = task;
        numberOfTasks++;
        TextUI.printStatements(TASK_ADDED_MESSAGE,
                String.format(DOUBLE_SPACE_PREFIX_STRING_FORMAT, task),
                String.format(TASK_TOTAL_TASKS_STRING_FORMAT, numberOfTasks));
    }

    /**
     * Prints all tasks (tasks are numbered based on addition).
     */
    private static void printAllTasks() {
        TextUI.printStatement(LIST_TASK_MESSAGE);
        for (int i = 0; i < numberOfTasks; i++) {
            Task task = tasks[i];
            TextUI.printStatement(String.format("%d.%s", i + 1, task));
        }
    }

    /**
     * Lists all tasks.
     */
    private static void listTasks() {
        if (numberOfTasks == 0) {
            TextUI.printStatements(LIST_NO_TASK_MESSAGE);
        } else {
            TextUI.printHorizontalLine();
            printAllTasks();
            TextUI.printHorizontalLine();
        }
    }

    /**
     * Marks a task as done based on the order of the list.
     *
     * @param commandArgs this should contain the task number to mark as done
     */
    private static void markTaskDone(String commandArgs) {
        try {
            String argValue = parseArgument(commandArgs, null);
            if (argValue == null) {
                throw new Exception(ERROR_INVALID_TASK_NUMBER_MESSAGE);
            }
            int taskNumber = Integer.parseInt(argValue);
            if (taskNumber > 0 && taskNumber <= numberOfTasks) {
                int taskIndex = taskNumber - 1;
                tasks[taskIndex].setDone(true);
                Task task = tasks[taskIndex];
                TextUI.printStatements(TASK_MARK_AS_DONE_FORMAT,
                        String.format(DOUBLE_SPACE_PREFIX_STRING_FORMAT, task));
            } else {
                throw new Exception(ERROR_INVALID_TASK_NUMBER_MESSAGE);
            }
        } catch (Exception e) {
            TextUI.printError(ERROR_INVALID_TASK_NUMBER_MESSAGE);
        }
    }



    private static void exitProgram() {
        TextUI.printStatements(EXIT_MESSAGE);
        System.exit(0);
    }

    /**
     * Parses the user input and attempts to execute the command
     * with the arguments.
     */
    private static void executeCommand(String userInput) {
        String[] commandAndArgs = parseCommand(userInput);
        String commandName = commandAndArgs[0];
        String commandArgs = commandAndArgs[1];

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
            // Does not fallthrough, exits program instead.
            exitProgram();
        default:
            TextUI.printError(ERROR_COMMAND_MESSAGE);
        }
    }

    /**
     * Splits the user input into a command word and command arguments string.
     *
     * @return an array of size 2; first element is the command word
     *         and second element is the arguments string
     */
    private static String[] parseCommand(String userInput) {
        final String[] commandAndArgs = userInput.trim().split("\\s+", 2);
        // parsedCommand's length after split will always be more than zero.
        if (commandAndArgs.length == 1) {
            return new String[] {commandAndArgs[0], ""};
        }
        return commandAndArgs;
    }

    /**
     * Parses an argument value after a given token.
     * Reads until a next token or the end of commandArgs string.
     * If token is null, read from the start of the string until
     * a next token or the end of commandArgs string.
     *
     * @param commandArgs a full string of command arguments
     * @param token a string representing an option portion of an argument e.g. "/by"
     * @return the argument value after the given token
     */
    private static String parseArgument(String commandArgs, String token) {
        int readFromIndex = 0;
        int readUntilIndex = commandArgs.length();
        boolean hasToken = (token != null) && (commandArgs.contains(token));
        boolean hasMissingToken = (token != null) && !(commandArgs.contains(token));
        if (hasToken) {
            readFromIndex = commandArgs.indexOf(token) + token.length();
        } else if (hasMissingToken) {
            return null;
        }
        int result = commandArgs.indexOf("/", readFromIndex);
        if (result != -1) {
            readUntilIndex = result;
        }
        return commandArgs.substring(readFromIndex, readUntilIndex).trim();
    }

    /**
     * Checks if argument value string is empty.
     *
     * @param argValue argument value of a command argument
     * @return true if empty, false if not empty
     */
    private static boolean isArgumentValueEmpty(String argValue) {
        return (argValue == null) || (argValue.length() == 0);
    }

    public static void main(String[] args) {
        TextUI.printWelcomeMessage();
        while (true) {
            String userCommand = TextUI.getUserInput();
            executeCommand(userCommand);
        }
    }
}
