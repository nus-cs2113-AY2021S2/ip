package duke.user_interface;

import java.util.Scanner;

public class UserInterface {

    private static final String DIVIDER = "____________________________________________________________";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final Scanner scanner = new Scanner(System.in);

    public void showWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        printDivider();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printDivider();
    }

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

    public void showExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    public void printFeedback(String feedback) {
        if (feedback != null) {
            System.out.println(feedback);
            printDivider();
        }
    }
}
