package duke;

import java.util.Scanner;

public class Parser {
    public static final int DONE_START = 5;
    public static final int TODO_START = 5;
    public static final int EVENT_START = 6;
    public static final int DEADLINE_START = 9;
    public static final int DELETE_START = 7;
    public static Scanner in = new Scanner(System.in);
    public static String input;

    static void verifyValidInput(String input, Command c) throws EmptyInputException, InvalidEventTimeException, InvalidDeadlineTimeException {
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

    private static boolean validDeadlineTime(String input) {
        return input.substring(getTimePosition(input), getTimePosition(input) + 3).equals("/by");
    }

    private static boolean validEventTime(String input) {
        return (input.substring(getTimePosition(input), getTimePosition(input) + 3).equals("/at"));
    }

    private static boolean isEmptyInput(String input, Command c) {
        return switch (c) {
            case TODO -> (input.substring(TODO_START).strip().equals(""));
            case EVENT -> input.substring(EVENT_START, getTimePosition(input)).strip().equals("");
            case DEADLINE -> (input.substring(DEADLINE_START, getTimePosition(input)).strip().equals(""));
            default -> false;
        };
    }

    static int getTimePosition(String input) {
        return input.indexOf('/');
    }

    static String getTime(String input) {
        int timePosition = getTimePosition(input);
        return input.substring(timePosition + 3);
    }

    static int getCompletedTaskIndex(String input) {
        return (Integer.parseInt(input.substring(DONE_START)) - 1);
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

    public static void executeCommand(String input, Command c) throws InvalidCommandException, EmptyInputException, InvalidEventTimeException, InvalidDeadlineTimeException {
        switch (c) {
        case LIST:
            Ui.listBeginMessage();
            TaskList.enumerateTasks();
            break;
        case DONE:
            TaskList.markTaskAsDone(input);
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
}
