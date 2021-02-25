package duke.ui;

import java.io.InputStream;
import java.util.Scanner;

import static duke.common.Messages.ERROR_PREFIX_MESSAGE;
import static duke.common.Messages.LOGO;
import static duke.common.Messages.WELCOME_MESSAGE;

public class TextUI {
    private static final String HORIZONTAL_LINE = "_".repeat(65);
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
     */
    public void printStatement(String statement) {
        String[] fragments = statement.split("\\R");
        for (String f : fragments) {
            System.out.println("\t " + f);
        }
    }

    /**
     * Prints statements within horizontal line borders.
     */
    public void printStatements(String... statement) {
        printHorizontalLine();
        for (String s : statement) {
            printStatement(s);
        }
        printHorizontalLine();
    }

    public void printError(String errorDescription) {
        printHorizontalLine();
        printStatement(String.format("%s %s", ERROR_PREFIX_MESSAGE, errorDescription));
        printHorizontalLine();
    }

    public void printWelcomeMessage() {
        printStatements(LOGO, WELCOME_MESSAGE);
    }
}
