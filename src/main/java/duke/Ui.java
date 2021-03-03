package duke;

/**
 * Represents the user interface that interacts with the user
 */
public class Ui {
    /**
     * print a welcome message when the program is launched
     */
    public static void printStartMessage() {
        String STARTMESSAGE = "____________________________________________________________\n"
                + "Hello! I'm Duke.Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(STARTMESSAGE);
    }

    /**
     * prints the logo of this program
     */
    public static void printLogo() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + LOGO);

    }

    /**
     * prints an exit message when user enters "bye"
     */
    public static void printExitMessage() {
        String ENDMESSAGE = "############################################################\n"
                + "Bye. Hope to see you again soon!\n"
                + "############################################################\n";
        System.out.println(ENDMESSAGE);
    }

    /**
     * prints multiple "*" which acts as a divider line
     */
    public static void printDivider() {
        System.out.println("************************************************************");
    }

}
