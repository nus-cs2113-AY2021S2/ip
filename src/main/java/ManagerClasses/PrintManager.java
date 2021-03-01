package ManagerClasses;

/**
 * Handles the UI elements of Duke. It can print the greeting, bye and help messages.
 */
public class PrintManager {
    private static final String BREAK_LINE = "------------------------------------------------------------";

    /**
     * Prints Duke logo on the terminal.
     */
    public static void showLogo() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(BREAK_LINE + "\n" + logo + "\n" + BREAK_LINE);
    }

    /**
     * Prints the breakline on the terminal.
     */
    public static void printBreakLine() {
        System.out.println(BREAK_LINE);
    }

    /**
     * Prints the greeting message in the terminal.
     */
    public static void showGreetMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printBreakLine();
    }

    /**
     * Prints the bye message in the terminal.
     */
    public static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printBreakLine();
    }

    /**
     * Prints the help message in the terminal. The help message entails the format that the user need to adhere to.
     */
    public static void printHelpMessage() {
        System.out.print("Help message:\n"
                + "* Adding a todo task -> 'todo <description>'\n"
                + "* Adding a deadline -> 'deadline <description> /by <when>'\n"
                + "* Adding an event -> 'event <description> /at <when>'\n"
                + "* Marking a task as done -> 'done <task number>'\n"
                + "* Listing all tasks -> 'list'\n"
                + "* Deleting a task -> 'delete <task number>'\n"
                + "* Searching for tasks by keyword -> 'find <keyword>'\n"
                + "* Exit -> 'bye'\n");
    }
}
