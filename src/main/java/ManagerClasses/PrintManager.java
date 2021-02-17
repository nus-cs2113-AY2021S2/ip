package ManagerClasses;

public class PrintManager {
    private static final String BREAK_LINE = "------------------------------------------------------------";

    public static void showLogo() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(BREAK_LINE + "\n" + logo + "\n" + BREAK_LINE);
    }

    public static void printBreakLine() {
        System.out.println(BREAK_LINE);
    }

    public static void showGreetMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printBreakLine();
    }

    public static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printBreakLine();
    }

    public static void printHelpMessage() {
        System.out.print("Help message:\n"
                + "* Add deadline -> 'deadline <description> /by <when>'\n"
                + "* Add event -> 'event <description> /at <when>'\n"
                + "* Add todo task -> 'todo <description>'\n"
                + "* Show list -> 'list'\n"
                + "* Mark as done -> 'done <task number>'\n"
                + "* Delete task -> 'delete <task number>'\n"
                + "* Exit -> 'bye'\n");
    }
}
