import java.util.Scanner;

public class Duke {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\n"
            + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public static Task[] tasks = new Task[100];
    public static int totalTasks = 0;

    public static void recordTask(String taskDescription) {
        if (taskDescription == null || taskDescription.length() == 0) {
            return;
        }
        tasks[totalTasks] = new Task(taskDescription);
        totalTasks++;
        printHorizontalLine();
        System.out.printf("\t added: %s", taskDescription);
        System.out.println();
        printHorizontalLine();
    }

    public static void listTasks() {
        printHorizontalLine();
        if (totalTasks == 0) {
            printStatement("No task in record.");
        } else {
            for (int i = 0; i < totalTasks; i++) {
                Task task = tasks[i];
                System.out.printf("\t %d.[%s] %s", i + 1, task.getStatusIcon(), task.description);
                System.out.println();
            }
        }
        printHorizontalLine();
    }

    public static void markTaskDone(int taskNum) {
        printHorizontalLine();
        if (taskNum > 0 && taskNum <= totalTasks) {
            tasks[taskNum - 1].isDone = true;
            Task task = tasks[taskNum - 1];
            printStatement("Nice! I've marked this task as done:");
            System.out.printf("\t   [%s] %s", task.getStatusIcon(), task.description);
            System.out.println();
        } else {
            printStatement("Oops, this task number does not exist.");
        }
        printHorizontalLine();
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
                if (line.startsWith("done")) {
                    try {
                        int taskNum = Integer.parseInt(line.substring(5));
                        markTaskDone(taskNum);
                    } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                        printHorizontalLine();
                        printStatement("Oops, invalid done command.");
                        printHorizontalLine();
                        break;
                    }
                } else {
                    recordTask(line);
                }
            }
        }
        printStatements(false, new String[]{EXIT_MESSAGE});
    }
}
