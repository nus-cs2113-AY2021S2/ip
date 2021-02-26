package duke;

import duke.exception.EmptyStringException;

import java.io.InputStream;
import java.io.PrintStream;

import java.util.Scanner;

/**
 * Represent the Text UI of the application.
 */
public class Ui {
    private final Scanner input;
    private final PrintStream output;

    private static final String BORDER = "_________________________________________________________________\n";
    private static final String LINE_PREFIX = "\t ";
    private static final String LS = System.lineSeparator();

    public Ui() {
        this(System.in,System.out);
    }

    public Ui(InputStream input, PrintStream output) {
        this.input = new Scanner(input);
        this.output = output;
    }

    /**
     * Reads the lines that user entered.
     *
     * @return the line that user entered.
     */
    public String getUserInput() {
        return input.nextLine();
    }

    /**
     * Print welcome message to user when starting the application.
     */
    public void showWelcomeMessage() {
        System.out.print(LINE_PREFIX + BORDER
                + LINE_PREFIX + "Welcome to Task Tracker!\n"
                + LINE_PREFIX + "This is your assistant Jack.\n"
                + LINE_PREFIX + "How may I assist you ?\n"
                + LINE_PREFIX + BORDER);
    }

    /**
     * Print results of the message to user after entering their input.
     *
     * @param message results of user command.
     */
    public void showMessage(String... message) {
        output.print(LINE_PREFIX + BORDER);
        for (String m : message) {
            output.print(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
        output.print(BORDER);
    }

    /**
     * Print load failure message when system unable load the file.
     */
    public void showLoadError() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "System unable to find directory..." + "\n"
                + LINE_PREFIX + "Load failed.\n"
                + LINE_PREFIX + "Creating new directory...\n"
                + LINE_PREFIX + "Directory created successfully.\n"
                + LINE_PREFIX + BORDER);
    }

    /**
     * Print load success message when system able to load the file.
     */
    public void showLoadSuccess() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "System able to find directory..." + "\n"
                + LINE_PREFIX + "Load successful.\n"
                + LINE_PREFIX + BORDER);
    }

    /**
     * Print error message when user entered empty string.
     */
    public void showErrorMessage() {
        String errorMessage = "☹ OOPS!!! Sorry I don't understand what you mean.\n";
        showMessage(errorMessage);
    }

    /**
     * Print exit message when user entered exit command.
     */
    public void showExitMessage() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "Good Bye. Hope to see you again soon!\n"
                + LINE_PREFIX + BORDER);
    }

    /**
     * Print save message when system able to save the data successful.
     */
    public void showSavedMessage() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "Data has been saved.\n"
                + LINE_PREFIX + BORDER);
    }


}
