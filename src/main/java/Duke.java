import java.util.Arrays;
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

    public static String[] tasks = new String[100];
    public static int totalTasks = 0;

    public static void recordTask(String task) {
        if (task == null || task.length() == 0) {
            return;
        }
        tasks[totalTasks] = task;
        totalTasks++;
        printHorizontalLine();
        System.out.printf("\t added: %s", task);
        System.out.println();
        printHorizontalLine();
    }

    public static void listTasks() {
        printStatements(true, Arrays.copyOf(tasks, totalTasks));
    }

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

    private static void printStatement(String statement, int number) {
        System.out.printf("\t %d. %s", number, statement);
        System.out.println();
    }

    public static void printStatements(boolean printNumber, String[] statements) {
        printHorizontalLine();
        for (int i = 0; i < statements.length; i++) {
            String statement = statements[i];
            if (printNumber) {
                printStatement(statement, i + 1);
            } else {
                printStatement(statement);
            }
        }
        printHorizontalLine();
    }

    public static void main(String[] args) {
        boolean onLoop = true;
        Scanner in = new Scanner(System.in);

        printStatements(false, new String[]{LOGO, WELCOME_MESSAGE});

        while (onLoop) {
            String line = in.nextLine().trim();
            switch (line) {
            case "bye":
                onLoop = false;
                break;
            case "list":
                listTasks();
                break;
            default:
                recordTask(line);
            }
        }
        printStatements(false, new String[]{EXIT_MESSAGE});
    }
}
