import java.util.Scanner;

public class Duke {
    private static TaskList taskList = new TaskList();
    private static Scanner in = initialiseInput();
    private static Command command;
    private static String input;

    /**
     * Main entry point of the application.
     * Initializes the application and starts the interaction with the user.
     */
    public static void main(String[] args) {
        printInitialMsg();
        do {
            scanInput();
            outputReaction();
        } while (canContinue());
    }

    private static void printInitialMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void scanInput() {
        input = takeInput(in);
        command = processInput(taskList, input);
    }

    private static void outputReaction() {
        switch (command) {
        case ADD:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            // Fallthrough
        case TODO:
            System.out.println("Added: " + taskList.getTask());
            break;
        case BYE:
            System.out.println("Bye. Hope to see you again soon!");
            break;
        case LIST:
            System.out.println(taskList);
            break;
        case DONE:
            int taskNum = getTaskNum(input);
            System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() +
                    taskList.getTask(taskNum - 1));
            break;
        case ERROR:
            System.out.println("Wrong Input!");
            break;
        }
    }

    private static boolean canContinue() {
        return command != Command.BYE;
    }

    private static Scanner initialiseInput() {
        return new Scanner(System.in);
    }

    private static String takeInput(Scanner in) {
        return in.nextLine();
    }

    private static Command processInput(TaskList taskList, String input) {
        switch (getCommand(input)) {
        case ADD:
            taskList.addTask(input, Command.ADD);
            break;
        case TODO:
            taskList.addTask(input, Command.TODO);
            break;
        case DEADLINE:
            taskList.addTask(input, Command.DEADLINE);
            break;
        case EVENT:
            taskList.addTask(input, Command.EVENT);
            break;
        case DONE:
            int taskNum = getTaskNum(input);
            taskList.finishTask(taskNum - 1);
            break;
        default:
            break;
        }
        return getCommand(input);
    }

    private static Command getCommand(String input) {
        if (input.equals("list")) {
            return Command.LIST;
        }
        else if (input.equals("bye")) {
            return Command.BYE;
        }
        else if (input.startsWith("done ")) {
            return Command.DONE;
        }
        else if (input.startsWith("todo ")) {
            return Command.TODO;
        }
        else if (input.startsWith("deadline ")) {
            return Command.DEADLINE;
        }
        else if (input.startsWith("event ")) {
            return Command.EVENT;
        }
        else {
            return Command.ADD;
        }
    }

    private static int getTaskNum(String input) {
        return Integer.parseInt(input.replaceFirst("done ", ""));
    }
}
