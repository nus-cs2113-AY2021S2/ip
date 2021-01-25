import java.util.Scanner;

public class Duke {

    static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    static final String WELCOME_MESSAGE = "Hello! I'm Duke\n"
            + "What can I do for you?";
    static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public static void printHorizontalLine() {
        String hLine = "_".repeat(60);
        System.out.println("\t" + hLine);
    }

    private static void printStatement(String statement) {
        String[] fragments = statement.split("\\R");
        for (String fragment : fragments) {
            System.out.println("\t " + fragment);
        }
    }

    public static void printStatements(boolean printHead, boolean printFoot, String[] statements) {
        if (printHead) {
            printHorizontalLine();
        }
        for (String statement : statements) {
            printStatement(statement);
        }
        if (printFoot) {
            printHorizontalLine();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        printStatements(true, true, new String[]{LOGO, WELCOME_MESSAGE});
        while (true) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else {
                printStatements(true, true, new String[]{line});
            }
        }
        printStatements(true, true, new String[]{EXIT_MESSAGE});
    }
}
