package duke.ui;

import java.io.InputStream;
import java.util.Scanner;

import static duke.common.Messages.MESSAGE_ERROR_PREFIX;
import static duke.common.Messages.LOGO;
import static duke.common.Messages.MESSAGE_WELCOME;

public class TextUI {
    private static final String HORIZONTAL_LINE = "_".repeat(70);
    private final Scanner input;

    public TextUI() {
        this(System.in);
    }

    public TextUI(InputStream in) {
        input = new Scanner(in);
    }

    /**
     * Reads user input per line.
     * Ignores lines that contains only whitespace.
     *
     * @return trimmed line string entered by the user
     */
    public String getUserInput() {
        String userInput;
        do {
            userInput = input.nextLine().trim();
        } while (userInput.isEmpty());
        return userInput;
    }

    public void printHorizontalLine() {
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    /**
     * Prints a statement with a tab and spacing character.
     * Can print multiple lines if the statement string has line
     * breaks in it.
     *
     * @param statement a string containing sentence or paragraph
     *                  of sentences.
     */
    public void printStatement(String statement) {
        String[] fragments = statement.split("\\R");
        for (String f : fragments) {
            System.out.println("\t " + f);
        }
    }

    /**
     * Prints statements within horizontal line borders.
     *
     * @param statement one or more string(s) containing sentence
     *                  or paragraph of sentences.
     */
    public void printStatements(String... statement) {
        printHorizontalLine();
        for (String s : statement) {
            printStatement(s);
        }
        printHorizontalLine();
    }

    /**
     * Prints the error prefix and the subsequent error message.
     *
     * @param errorDescription a description of an error or
     *                         message pertaining to something
     *                         that failed to be processed.
     */
    public void printError(String errorDescription) {
        printHorizontalLine();
        printStatement(String.format("%s %s", MESSAGE_ERROR_PREFIX, errorDescription));
        printHorizontalLine();
    }

    public void printWelcomeMessage() {
        printStatements(LOGO, MESSAGE_WELCOME);
    }
}
