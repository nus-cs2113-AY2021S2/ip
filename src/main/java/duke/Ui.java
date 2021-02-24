package duke;

import duke.exception.EmptyStringException;

import java.io.InputStream;
import java.io.PrintStream;

import java.util.Scanner;

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

    public String getUserCommand() {
        return input.nextLine();
    }

    public void showWelcomeMessage() {
        System.out.print(LINE_PREFIX + BORDER
                + LINE_PREFIX + "Welcome to Task Tracker!\n"
                + LINE_PREFIX + "This is your assistant Jack.\n"
                + LINE_PREFIX + "How may I assist you ?\n"
                + LINE_PREFIX + BORDER);
    }

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
                + LINE_PREFIX + "Creating new directory...\n"
                + LINE_PREFIX + "Directory created successfully.\n"
                + LINE_PREFIX + BORDER);
    }

    public void showLoadSuccess() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "System able to find directory..." + "\n"
                + LINE_PREFIX + "Load successful.\n"
                + LINE_PREFIX + BORDER);
    }

    public void showErrorMessage(Exception exception) {
        String errorMessage = "";
        if (exception instanceof EmptyStringException) {
            errorMessage = "☹ OOPS!!! Sorry I don't understand what you mean.\n";
        }
        showMessage(errorMessage);
    }

    public void showExitMessage() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "Good Bye. Hope to see you again soon!\n"
                + LINE_PREFIX + BORDER);
    }

    public void showSavedMessage() {
        System.out.print(LINE_PREFIX +BORDER
                + LINE_PREFIX + "Data has been saved.\n"
                + LINE_PREFIX + BORDER);
    }


}
