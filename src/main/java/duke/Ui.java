package duke;

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

    public String getUserInput() {
        return input.nextLine();
    }

    public void showWelcomeMessage() {
        System.out.print(LINE_PREFIX + BORDER
                + LINE_PREFIX + "Welcome to Task Tracker!\n"
                + LINE_PREFIX + "This is your assistant Jack.\n"
                + LINE_PREFIX + "How may I assist you ?\n"
                + LINE_PREFIX + BORDER);
    }

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

    public void showLoadError() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "System unable to find directory..." + "\n"
                + LINE_PREFIX + "Load failed.\n"
                + LINE_PREFIX + BORDER);
    }

    public void showLoadSuccess() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "System able to find directory..." + "\n"
                + LINE_PREFIX + "Load successful.\n"
                + LINE_PREFIX + BORDER);
    }

    public void showErrorMessage() {
        String errorMessage = "☹ OOPS!!! Sorry I don't understand what you mean.\n";
        showMessage(errorMessage);
    }

    public void showExitMessage() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "Good Bye. Hope to see you again soon!\n"
                + LINE_PREFIX + BORDER);
    }

    public void showSaveSuccess() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "Data has been saved.\n"
                + LINE_PREFIX + BORDER);
    }

    public void showSaveFailed() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "Save failed. System unable to find directory\n"
                + LINE_PREFIX + BORDER);
    }

    public void showCreateDirectorySuccess() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "Creating new directory...\n"
                + LINE_PREFIX + "Directory created successfully.\n"
                + LINE_PREFIX + BORDER);
    }
    public void showFailToCreateDirectory() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "Unable create directory.. Please try again later. \n"
                + LINE_PREFIX + BORDER);
    }



}
