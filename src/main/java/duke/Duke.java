package duke;

import duke.io.FileIO;
import duke.io.TextUI;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.io.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    private static final String DOUBLE_SPACE_PREFIX_STRING_FORMAT = "  %s";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String LIST_TASK_MESSAGE = "Here are the tasks in your list:";
    private static final String LIST_NO_TASK_MESSAGE = "No task in record.";
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String TASK_TOTAL_TASKS_STRING_FORMAT = "Now you have %d tasks in the list.";
    private static final String TASK_MARK_AS_DONE_FORMAT = "Nice! I've marked this task as done:";
    private static final String ERROR_COMMAND_MESSAGE = "I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_EMPTY_DEADLINE_BY_MESSAGE = "The deadline's /by argument cannot be empty.";
    private static final String ERROR_EMPTY_EVENT_AT_MESSAGE = "The event's /at argument cannot be empty.";
    private static final String ERROR_WRITE_TO_FILE_MESSAGE = "Unable to write to file. :<(";

    private static final String ERROR_EMPTY_TASK_NUMBER_MESSAGE = "Missing task number,"
            + "please specify a valid task number.";
    private static final String ERROR_INVALID_TASK_NUMBER_MESSAGE = "The task number you've entered is invalid.";
    private static final String ERROR_NOT_A_TASK_NUMBER_MESSAGE =
            "Please enter a valid positive integer for a task number.";
    private static final String ERROR_EMPTY_TASK_STRING_FORMAT = "The description of a %s cannot be empty.";

    private static final int MAX_NUMBER_OF_TASKS = 100;
    private static Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];
    private static int numberOfTasks = 0;

    /**
     * Records a new Todo task into the global task array.
     * Ensures the task description is given in {@code commandArgs}
     * Fails if task description argument value is invalid.
     *
     * @param commandArgs this should contain task description
     * @see #validateTodoArguments(String) 
     */
    private static void recordTodo(String commandArgs) {
        try {
            String taskDescription = validateTodoArguments(commandArgs);
            recordTask(new Todo(taskDescription));
        } catch (DukeException e) {
            TextUI.printError(e.getMessage());
        }
    }

    private static String validateTodoArguments(String commandArgs) throws DukeException {
        String taskDescription = parseArgument(commandArgs, null);
        if (isArgumentValueEmpty(taskDescription)) {
            throw new DukeException(String.format(ERROR_EMPTY_TASK_STRING_FORMAT, "todo"));
        }
        return taskDescription;
    }

    /**
     * Records a new Deadline task into the global task array.
     * Ensures the task description and deadline-by is given in {@code commandArgs}
     * Fails if any argument value is invalid.
     *
     * @param commandArgs this should contain task description and deadline-by
     * @see #validateDeadlineArguments(String) 
     */
    private static void recordDeadline(String commandArgs) {
        try {
            String[] deadlineArgValues = validateDeadlineArguments(commandArgs);
            recordTask(new Deadline(deadlineArgValues[0], deadlineArgValues[1]));
        } catch (DukeException e) {
            TextUI.printError(e.getMessage());
        }
    }

    private static String[] validateDeadlineArguments(String commandArgs) throws DukeException {
        String taskDescription = parseArgument(commandArgs, null);
        String deadlineBy = parseArgument(commandArgs, Command.DEADLINE_BY_TOKEN);
        if (isArgumentValueEmpty(taskDescription)) {
            throw new DukeException(String.format(ERROR_EMPTY_TASK_STRING_FORMAT, "deadline"));
        }
        if (isArgumentValueEmpty(deadlineBy)) {
            throw new DukeException(ERROR_EMPTY_DEADLINE_BY_MESSAGE);
        }
        return new String[] {taskDescription, deadlineBy};
    }

    /**
     * Records a new Event task into the global task array.
     * Ensures the task description and event-at is given in {@code commandArgs}
     * Fails if any argument value is invalid.
     *
     * @param commandArgs this should contain task description and event-at
     * @see #validateEventArguments(String) 
     */
    private static void recordEvent(String commandArgs) {
        try {
            String[] eventArgValues = validateEventArguments(commandArgs);
            recordTask(new Event(eventArgValues[0], eventArgValues[1]));
        } catch (DukeException e) {
            TextUI.printError(e.getMessage());
        }
    }

    private static String[] validateEventArguments(String commandArgs) throws DukeException {
        String taskDescription = parseArgument(commandArgs, null);
        String eventAt = parseArgument(commandArgs, Command.EVENT_AT_TOKEN);
        if (isArgumentValueEmpty(taskDescription)) {
            throw new DukeException(String.format(ERROR_EMPTY_TASK_STRING_FORMAT, "event"));
        }
        if (isArgumentValueEmpty(eventAt)) {
            throw new DukeException(ERROR_EMPTY_EVENT_AT_MESSAGE);
        }
        return new String[] {taskDescription, eventAt};
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
        try {
            FileIO.writeTasksToFile(new ArrayList<>(Arrays.asList(Arrays.copyOf(tasks, numberOfTasks))));
        } catch (IOException e) {
            TextUI.printError(ERROR_WRITE_TO_FILE_MESSAGE);
        }
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
     * @see #validateTaskDoneArguments(String)
     */
    private static void markTaskDone(String commandArgs) {
        try {
            int taskNumber = validateTaskDoneArguments(commandArgs);
            int taskIndex = taskNumber - 1;
            tasks[taskIndex].setDone(true);
            Task task = tasks[taskIndex];
            TextUI.printStatements(TASK_MARK_AS_DONE_FORMAT,
                    String.format(DOUBLE_SPACE_PREFIX_STRING_FORMAT, task));
            try {
                FileIO.writeTasksToFile(new ArrayList<>(Arrays.asList(Arrays.copyOf(tasks, numberOfTasks))));
            } catch (IOException e) {
                TextUI.printError(ERROR_WRITE_TO_FILE_MESSAGE);
            }
        } catch (NumberFormatException e) {
            TextUI.printError(ERROR_NOT_A_TASK_NUMBER_MESSAGE);
        } catch (DukeException e) {
            TextUI.printError(e.getMessage());
        }
    }

    private static int validateTaskDoneArguments(String commandArgs) throws DukeException, NumberFormatException {
        String argValue = parseArgument(commandArgs, null);
        if (argValue == null) {
            throw new DukeException(ERROR_EMPTY_TASK_NUMBER_MESSAGE);
        }
        int taskNumber = Integer.parseInt(argValue);
        if (taskNumber >= MAX_NUMBER_OF_TASKS) {
            // Prevents ArrayIndexOutOfBoundsException beyond upper limit:
            // -> Upper limit: MAX_NUMBER_OF_TASKS - 1
            throw new DukeException(ERROR_INVALID_TASK_NUMBER_MESSAGE);
        }
        if (taskNumber <= 0 || taskNumber > numberOfTasks) {
            // Prevents ArrayIndexOutOfBoundsException beyond lower limit:
            // -> Lower limit: 0
            // Prevents NullPointerException beyond index (numberOfTasks - 1).
            throw new DukeException(ERROR_INVALID_TASK_NUMBER_MESSAGE);
        }
        return taskNumber;
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
        case Command.LIST_WORD:
            listTasks();
            break;
        case Command.TODO_WORD:
            recordTodo(commandArgs);
            break;
        case Command.DEADLINE_WORD:
            recordDeadline(commandArgs);
            break;
        case Command.EVENT_WORD:
            recordEvent(commandArgs);
            break;
        case Command.DONE_WORD:
            markTaskDone(commandArgs);
            break;
        case Command.BYE_WORD:
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
        tasks = FileIO.retrieveTasksFromFile();
        numberOfTasks = tasks.length;
        tasks = Arrays.copyOf(tasks, MAX_NUMBER_OF_TASKS);
        while (true) {
            String userCommand = TextUI.getUserInput();
            executeCommand(userCommand);
        }
    }
}
