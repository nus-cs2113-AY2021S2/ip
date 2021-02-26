package duke;

import java.util.Scanner;

/**
 * A class containing the methods necessary to perform parse the user input in the Duke program
 * and contains the fixed-length constants of each command to help parse the information
 * Also executes the commands after receiving user input.
 */
public class Parser {
    public static final int DONE_START = 5;
    public static final int FIND_START = 5;
    public static final int TODO_START = 5;
    public static final int EVENT_START = 6;
    public static final int DEADLINE_START = 9;
    public static final int DELETE_START = 7;
    public static Scanner in = new Scanner(System.in);
    public static String input;

    /**
     * Main driver command for Duke program that parses user input and executes the appropriate command
     * Using a switch statement to choose the corresponding command,
     * and calls methods from Ui and TaskList classes to perform the appropriate actions
     * Throws exceptions if requirements are violated
     *
     * @param input the user input from Duke
     * @param c     the command from Parser to be executed
     * @throws InvalidCommandException      if user input an unrecognised Command
     * @throws EmptyInputException          if empty string as input
     * @throws InvalidEventTimeException    if /at is not found when adding a new Event
     * @throws InvalidDeadlineTimeException if /by is not found when adding a new Deadline
     */
    public static void executeCommand(String input, Command c)
            throws InvalidCommandException, EmptyInputException, InvalidEventTimeException,
                    InvalidDeadlineTimeException {
        switch (c) {
        case LIST:
            Ui.listBeginMessage();
            TaskList.enumerateTasks();
            break;
        case DONE:
            TaskList.markTaskAsDone(input);
            break;
        case FIND:
            Ui.findTaskMessage();
            TaskList.findTasks(input);
            break;
        case DELETE:
            TaskList.deleteTask(input);
            TaskList.decrementTasks();
            break;
        case TODO:
            verifyValidInput(input, c);
            TaskList.addNewTodo(input);
            Ui.confirmNewTaskMessage();
            TaskList.incrementTasks();
            break;
        case EVENT:
            verifyValidInput(input, c);
            TaskList.addNewEvent(input);
            Ui.confirmNewTaskMessage();
            TaskList.incrementTasks();
            break;
        case DEADLINE:
            verifyValidInput(input, c);
            TaskList.addNewDeadline(input);
            Ui.confirmNewTaskMessage();
            TaskList.incrementTasks();
            break;
        case INVALID:
            throw new InvalidCommandException();

        }
    }

    /**
     * Ensures that the input for adding a new Task is valid
     * Such as a valid time for Events and Deadlines
     * and prevents empty task inputs.
     * Throws exceptions if requirements are violated
     *
     * @param input the user input from Duke
     * @param c     the command from Parser to be executed
     * @throws EmptyInputException          if empty string as input
     * @throws InvalidEventTimeException    if /at is not found when adding a new Event
     * @throws InvalidDeadlineTimeException if /by is not found when adding a new Deadline
     */
    static void verifyValidInput(String input, Command c)
            throws EmptyInputException, InvalidEventTimeException, InvalidDeadlineTimeException {
        if (isEmptyInput(input, c)) {
            throw new EmptyInputException();
        }

        switch (c) {
        case EVENT:
            if (validEventTime(input)) {
                break;
            } else {
                throw new InvalidEventTimeException();
            }
        case DEADLINE:
            if (validDeadlineTime(input)) {
                break;
            } else {
                throw new InvalidDeadlineTimeException();
            }
        }
    }

    /**
     * Verifies that the Deadline Time as part of the input is valid
     * Ensures that the substring "/by" is part of the input
     *
     * @param input the user input from Duke
     * @return Nothing.
     */
    private static boolean validDeadlineTime(String input) {
        return input.substring(getTimePosition(input), getTimePosition(input) + 3).equals("/by");
    }

    /**
     * Verifies that the Event Time as part of the input is valid
     * Ensures that the substring "/at" is part of the input
     *
     * @param input the user input from Duke
     * @return Nothing.
     */
    private static boolean validEventTime(String input) {
        return (input.substring(getTimePosition(input), getTimePosition(input) + 3).equals("/at"));
    }

    /**
     * Determines if the input when adding a new Task is empty
     *
     * @param input the user input from Duke
     * @param c     the command from Parser to be executed
     * @return Nothing.
     */
    private static boolean isEmptyInput(String input, Command c) {
        return switch (c) {
            case TODO -> (input.substring(TODO_START).strip().equals(""));
            case EVENT -> input.substring(EVENT_START, getTimePosition(input)).strip().equals("");
            case DEADLINE -> (input.substring(DEADLINE_START, getTimePosition(input)).strip().equals(""));
            default -> false;
        };
    }

    /**
     * Returns the position of the '/' character in the input string.
     * If the position is not found, the parent function "verifyValidInput" would throw exception.
     *
     * @param input the user input from Duke
     * @return '/' position
     */
    static int getTimePosition(String input) {
        return input.indexOf('/');
    }

    /**
     * Returns the position where the time information should start in the input string.
     * If the position is not found, the parent function "verifyValidInput" would throw exception.
     *
     * @param input the user input from Duke
     * @return time information position
     */
    static String getTime(String input) {
        int timePosition = getTimePosition(input);
        return input.substring(timePosition + 3);
    }

    /**
     * Returns the integer that should be found after the "Done" command
     * that represents the task index to be marked as completed.
     * Throws exception in Duke if the integer is invalid.
     *
     * @param input the user input from Duke
     * @return Task Index to be marked as completed
     */
    static int getCompletedTaskIndex(String input) {
        return (Integer.parseInt(input.substring(DONE_START)) - 1);
    }

    static String getTaskToFind(String input) {
        return input.substring(FIND_START);
    }

    static boolean isBye() {
        return input.equals("bye");
    }

    static boolean isDeadline() {
        return input.length() > 8 && input.substring(0, 8).equals("deadline");
    }

    static boolean isEvent() {
        return input.length() > 5 && input.substring(0, 5).equals("event");
    }

    static boolean isTodo() {
        return input.length() > 4 && input.substring(0, 4).equals("todo");
    }

    static boolean isFind() {
        return input.length() > 4 && input.substring(0, 4).equals("find");
    }

    static boolean isList() {
        return input.equals("list");
    }

    static boolean isDelete() {
        return input.length() > 6 && input.substring(0, 6).equals("delete");
    }

    static boolean isDone() {
        return input.length() > 4 && input.substring(0, 4).equals("done");
    }

    static String getInput(Scanner in) {
        String input;
        input = in.nextLine();
        return input;
    }

}
