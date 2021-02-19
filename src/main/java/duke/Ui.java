package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static duke.Messages.*;

/**
 * UI of the application.
 */
public class Ui {

    private static PrintStream out;
    private final Scanner in;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        Ui.out = out;
    }

    /**
     * Generates and prints the bye message upon the termination of the application.
     */
    public static void printBye() {
        showToUser(MESSAGE_GOODBYE);
    }

    /**
     * Shows message(s) to the user.
     */
    public static void showToUser(String... message) {
        for (String m : message) {
            out.println(m.replace("\n", LS));
        }
    }

    /**
     * Shows the divider line.
     */
    public static void showLine() {
        showToUser(DIVIDER);
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void showWelcome() {
        showToUser(LOGO, DIVIDER, MESSAGE_WELCOME, DIVIDER);
    }

    /**
     * Reads the text entered by the user.
     *
     * @return Full line command entered by the user.
     */
    public String readCommand() {
        return in.nextLine();
    }

    public void showLoadingError() {
        System.out.println(MESSAGE_LOAD_ERROR);
    }
}
