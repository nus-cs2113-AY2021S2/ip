package ui;

import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class TextUi {

    Scanner in = new Scanner(System.in);

    public TextUi() {
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     *
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    /** Prints the welcome message upon the start of the application. */
    public void showWelcomeMessage() {
        String LINEBREAK = "____________________________________________________________\n";
        String LOGO = "Hello from\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String GREETING =  " Hello! I'm Duke\n"
                + " What can I do for you?\n";
        String GOODBYE = "Bye, Hope to see you again soon!\n";
        System.out.println(LOGO);
        System.out.println(LINEBREAK + GREETING + LINEBREAK);
    }

    /** Prints the goodbye message at the end of the application. */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }


}
