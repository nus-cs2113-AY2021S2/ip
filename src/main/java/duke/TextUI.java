package duke;

import java.util.Scanner;

public class TextUI {
    private static final String HORIZONTAL_LINE = "_".repeat(65);
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\n"
            + "What can I do for you?";
    private static final String ERROR_PREFIX_MESSAGE = "ERROR: ";

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Reads user input per line.
     * Ignores lines that contains only whitespace.
     *
     * @return trimmed line string entered by the user
     */
    public static String getUserInput() {
        String userInput;
        do {
            userInput = SCANNER.nextLine().trim();
        } while (userInput.isEmpty());
        return userInput;
    }

    public static void printHorizontalLine() {
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    /**
     * Prints a statement with a tab and spacing character.
     * Can print multiple lines if the statement string has line
     * breaks in it.
     */
    public static void printStatement(String statement) {
        String[] fragments = statement.split("\\R");
        for (String f : fragments) {
            System.out.println("\t " + f);
        }
    }

    /**
     * Prints statements within horizontal line borders.
     */
    public static void printStatements(String... statement) {
        printHorizontalLine();
        for (String s : statement) {
            printStatement(s);
        }
        printHorizontalLine();
    }

    public static void printError(String errorDescription) {
        printHorizontalLine();
        printStatement(String.format("%s %s", ERROR_PREFIX_MESSAGE, errorDescription));
        printHorizontalLine();
    }

    public static void printWelcomeMessage() {
        printStatements(LOGO, WELCOME_MESSAGE);
    }
}
