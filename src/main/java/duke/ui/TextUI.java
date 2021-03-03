package duke.ui;

import static duke.common.Messages.WELCOME_MESSAGE;
import static duke.common.Messages.EXIT_MESSAGE;
import static duke.common.Messages.UNKNOWN_MESSAGE;
import static duke.common.Messages.LOGO;
import static duke.common.Messages.DIVIDER;
import static duke.tasks.Task.NO_DESCRIPTION;
import static duke.tasks.Task.NO_DATE;
import static duke.tasks.Task.NO_INDEX;
import static duke.tasks.Task.TODO_USAGE;
import static duke.tasks.Task.DEADLINE_USAGE;
import static duke.tasks.Task.EVENT_USAGE;
import static duke.tasks.Task.DONE_USAGE;
import static duke.tasks.Task.DELETE_USAGE;
import static duke.tasks.Task.FIND_USAGE;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Deals with input/output streams and data to be shown to the user. 
 */
public class TextUI {

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructor for the <code>TextUI</code> class. 
     */
    public TextUI() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void showWelcomeMessage() {
        printToScreen(DIVIDER, LOGO, WELCOME_MESSAGE, DIVIDER);
    }

    public void showExitMessage() {
        printToScreen(EXIT_MESSAGE);
    }

    public void showUnknownMessage() {
        printToScreen(DIVIDER, UNKNOWN_MESSAGE, DIVIDER);
    }

    /**
     * Prints a message to the output stream. 
     * 
     * @param message message to be printed
     */
    public void printToScreen(String... message) {
        for (String m : message) {
            out.println("\t" + m);
        }
    }

    /**
     * Reads the input from the input stream. 
     * 
     * @return user input as a String
     */
    public String readCommand() {
        String command = in.nextLine();
        return command;
    }

    public void noDescription(String type) {
        printToScreen(DIVIDER);
        printToScreen(NO_DESCRIPTION);
        printUsage(type);
        printToScreen(DIVIDER);
    }

    public void noDate(String type) {
        printToScreen(DIVIDER);
        printToScreen(NO_DATE);
        printUsage(type);
        printToScreen(DIVIDER);
    }

    public void noIndex(String type) {
        printToScreen(DIVIDER);
        printToScreen(NO_INDEX);
        printUsage(type);
        printToScreen(DIVIDER);
    }

    /**
     * Prints the usage hints of the particular command. 
     * 
     * @param type type of command of the hint to be shown
     */
    public void printUsage(String type) {
        switch(type) {
        case "todo":
            printToScreen("Usage: " + TODO_USAGE);
            break;
        case "event":
            printToScreen("Usage: " + EVENT_USAGE);
            break;
        case "deadline":
            printToScreen("Usage: " + DEADLINE_USAGE);
            break;
        case "done":
            printToScreen("Usage: " + DONE_USAGE);
            break;
        case "delete":
            printToScreen("Usage: " + DELETE_USAGE);
            break;
        case "find":
            printToScreen("Usage: " + FIND_USAGE);
            break;
        default:
            ;
        }
    }
}
