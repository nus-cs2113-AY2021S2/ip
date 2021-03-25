package duke.utilities;

import java.io.IOException;
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

    //@@author SimJJ96-reused
    /*Reused from https://github.com/se-edu/addressbook-level2/blob/master/src/
            seedu/addressbook/ui/TextUi.java*/
    public Ui() {
        this(System.in,System.out);
    }

    public Ui(InputStream input, PrintStream output) {
        this.input = new Scanner(input);
        this.output = output;
    }
    //@@author

    /**
     * Retrieves the next line of input entered by the user.
     *
     * @return string of the next input
     */
    public String getUserInput() {
        return input.nextLine();
    }

    /**
     * Print to user a welcome message.
     */
    public void showWelcomeMessage() {
        System.out.print(LINE_PREFIX + BORDER
                + LINE_PREFIX + "Welcome to Task Tracker!\n"
                + LINE_PREFIX + "This is your assistant Jack.\n"
                + LINE_PREFIX + "How may I assist you ?\n"
                + LINE_PREFIX + BORDER);
    }

    //@@author SimJJ96-reused
    /*Reused from https://github.com/se-edu/addressbook-level2/blob/master/src/
            seedu/addressbook/ui/TextUi.java*/
    /**
     * Format the result of the command and print the message.
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
    //@@author

    /**
     * Print error loading message to user when the required file does not exist.
     */
    public void showLoadError() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "System unable to find directory..." + "\n"
                + LINE_PREFIX + "Load failed.\n"
                + LINE_PREFIX + BORDER);
    }

    /**
     * Print success loading message to user when the required file exist.
     */
    public void showLoadSuccess() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "System able to find directory..." + "\n"
                + LINE_PREFIX + "Load successful.\n"
                + LINE_PREFIX + BORDER);
    }

    /**
     * Print the exception message when the program encounter an exception.
     *
     * @param exception the exception encountered
     */
    public void showExceptionMessage(Exception exception) {
        if (exception instanceof IOException) {
            showSaveFailedMessage();
        } else {
            showMessage(exception.getMessage());
        }
    }

    /**
     * Print exit message to give acknowledgement to user to end the program.
     */
    public void showExitMessage() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "Good Bye. Hope to see you again soon!\n"
                + LINE_PREFIX + BORDER);
    }

    /**
     * Print failure message in saving the data into the specified file.
     */
    public void showSaveFailedMessage() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "Save failed. System unable to find directory\n"
                + LINE_PREFIX + BORDER);
    }

    /**
     * Print success message in creating the directory.
     */
    public void showCreateDirectorySuccess() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "Creating new directory...\n"
                + LINE_PREFIX + "Directory created successfully.\n"
                + LINE_PREFIX + BORDER);
    }

    /**
     * Print failure message in creating the directory.
     */
    public void showFailToCreateDirectory() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "Unable create directory.. Please try again later. \n"
                + LINE_PREFIX + BORDER);
    }

}
