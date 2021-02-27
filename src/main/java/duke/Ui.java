package duke;

import java.util.ArrayList;

public class Ui {
    private static ArrayList<String> messages = new ArrayList<>();

    private static void dukePrinter() {
        System.out.print(Constants.LINE);
        for (String message : Ui.messages) {
            System.out.print(message);
            System.out.print(System.lineSeparator());
        }
        System.out.print(Constants.LINE);
        Ui.messages.clear();
    }

    /**
     * Prints each string in the ArrayList as a new line, in between 2 dashed lines
     *
     * @param messages Arraylist containing messages that are to be printed
     */
    public static void dukePrinter(ArrayList<String> messages) {
        System.out.print(Constants.LINE);
        for (String message: messages) {
            System.out.print(message);
            System.out.print(System.lineSeparator());
        }
        System.out.print(Constants.LINE);
    }

    public static void printToDoAdded(String description) {
        messages.add(String.format(Constants.ADD_MESSAGE, description));
        dukePrinter();
    }

    public static void printDeadlineAdded(String description, String dueDate) {
        messages.add(String.format(Constants.ADD_MESSAGE, description));
        messages.add(String.format(Constants.DEADLINE_DUE_DATE_MESSAGE, dueDate));
        dukePrinter();
    }

    public static void printEventAdded(String description, String time) {
        messages.add(String.format(Constants.ADD_MESSAGE, description));
        messages.add(String.format(Constants.EVENT_TIME_MESSAGE, time));
        dukePrinter();
    }

    public static void printTaskChecked(int taskNo, String statusSymbol, String description, int tasksRemaining) {
        messages.add(String.format(Constants.TASK_CHECKED_MESSAGE, taskNo, statusSymbol, description));
        if (tasksRemaining == 0) {
            messages.add(Constants.ALL_TASKS_CHECKED_MESSAGE);
        }
        dukePrinter();
    }

    public static void printTaskUnchecked(int taskNo, String statusSymbol, String description) {
        messages.add(String.format(Constants.TASK_UNCHECKED_MESSAGE, taskNo, statusSymbol, description));
        dukePrinter();
    }

    public static void printTaskDeleted(int taskNo, String description) {
        messages.add(String.format(Constants.TASK_DELETED_MESSAGE, taskNo, description));
        dukePrinter();
    }

    public static void printWelcomeMessage() {
        messages.add(Constants.WELCOME_MESSAGE);
        dukePrinter();
    }

    public static void printHelpMessage() {
        messages.add(Constants.TODO_INFO);
        messages.add(Constants.EVENT_INFO);
        messages.add(Constants.DEADLINE_INFO);
        messages.add(Constants.EVENT_INFO);
        messages.add(Constants.LIST_INFO);
        messages.add(Constants.DONE_INFO);
        messages.add(Constants.UNDO_INFO);
        messages.add(Constants.DELETE_INFO);
        messages.add(Constants.FIND_INFO);
        messages.add(Constants.BYE_INFO);
        dukePrinter();
    }

    public static void printEmptyListMessage() {
        messages.add(Constants.EMPTY_LIST_MESSAGE);
        dukePrinter();
    }

    public static void printTaskAlreadyCheckedMessage(int taskNo, String desc) {
        messages.add(String.format(Constants.TASK_ALREADY_CHECKED_MESSAGE, taskNo, desc));
        dukePrinter();
    }

    public static void printTaskNotCheckedMessage(int taskNo, String desc) {
        messages.add(String.format(Constants.TASK_NOT_CHECKED_MESSAGE, taskNo, desc));
        dukePrinter();
    }
    public static void printInvalidCommandMessage() {
        messages.add(Constants.INVALID_COMMAND_MESSAGE);
        dukePrinter();
    }

    /**
     * Prints an invalid argument message along with the reason
     *
     * @param reason Reason the argument is invalid
     */
    public static void printInvalidArgumentMessage(String reason) {
        messages.add(Constants.INVALID_ARGUMENT_MESSAGE);
        if (reason != null) {
            messages.add("Reason: " + reason);
        }
        dukePrinter();
    }

    public static void printGenericErrorMessage() {
        messages.add(Constants.GENERIC_ERROR_MESSAGE);
        dukePrinter();
    }

    public static void printByeMessage() {
        messages.add(Constants.BYE_MESSAGE);
        dukePrinter();
    }
}
