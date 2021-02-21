package duke.user_interface;

import java.util.Scanner;

/**
 * Handles input and output to the command line.
 */
public class UserInterface {

    private static final String DIVIDER = "____________________________________________________________";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message when the user
     * open the program.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        printDivider();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printDivider();
    }

    /**
     * Takes in the user input and sends it to the program.
     * @return user input
     */
    public String getUserInput() {
        String input;
        do {
            input = scanner.nextLine();
        } while (input.trim().isEmpty());
        printDivider();
        return input;
    }

    private void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays the exit message when the user ends the program.
     */
    public void showExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Displays the output from the execution of command to the user.
     * @param feedback To be shown to the user.
     */
    public void printFeedback(String feedback) {
        if (feedback != null) {
            System.out.println(feedback);
            printDivider();
        }
    }
}
