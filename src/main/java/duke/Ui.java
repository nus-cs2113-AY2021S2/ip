package duke;

import duke.task.Task;

public class Ui {
    private static final int ERR_MAX_CAPACITY = -5;
    private static final int ERR_NO_NAME = -4;
    private static final int ERR_OUT_OF_BOUNDS_MESSAGE = -3;
    private static final int ERR_WRONG_FORMAT_MESSAGE = -2;

    private static final String GREETING_MESSAGE = "Wagwan! I is Ali G. West side.\nWhat is we chattin' bout today?";
    private static final String GOODBYE_MESSAGE = "Goodbye, big up yourself, keep it real, respekt.";
    private static final String BORDER_LINE = "___________________________________________________________";
    private static final String OUT_OF_BOUNDS_MESSAGE = "You are accessing something that doesn't exist! Stop being an ignoranus.";
    private static final String WRONG_FORMAT_MESSAGE = "Are you spasticated? The format is wrong!";
    private static final String NO_NAME_MESSAGE = "Why you be trying to find something with no name? Ave' you been smoking me special stash?";
    private static final String EXCEED_CAPACITY_MESSAGE = "Maximum capacity reached";
    private static final String SET_TO_DONE_MESSAGE = " set to done. You're well smart innit?";

    public void printGreeting() {
        System.out.println(GREETING_MESSAGE);
        printBorderLine();
    }
    public void printBorderLine() {
        System.out.println(BORDER_LINE);
    }
    public void sayGoodbye() {
        System.out.println(GOODBYE_MESSAGE);
        printBorderLine();
    }

    /** Method used to print each item for "list" command */
    public void printListItem(int index, String type, String status, String name, String date) {
        System.out.println(index + ". " + type + "[" + status + "] " + name + " " + date);
    }
    public void printMarkedDone(String name) {
        System.out.println(name + SET_TO_DONE_MESSAGE);
    }
    /**
     * Prints appropriate error message according to error type.
     *
     * @param type type of error thrown.
     */
    public void printError(int type) {
        // No fallthrough required
        switch (type) {
        case ERR_OUT_OF_BOUNDS_MESSAGE:
            System.out.println(OUT_OF_BOUNDS_MESSAGE);
            break;
        case ERR_WRONG_FORMAT_MESSAGE:
            System.out.println(WRONG_FORMAT_MESSAGE);
            break;
        case ERR_NO_NAME:
            System.out.println(NO_NAME_MESSAGE);
            break;
        case ERR_MAX_CAPACITY:
            System.out.println(EXCEED_CAPACITY_MESSAGE);
        }
        printBorderLine();
    }
}
