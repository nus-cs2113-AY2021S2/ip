import java.util.Scanner;

public class Duke {
    public enum inputCommand {
        ADD, BYE, DONE, ERROR, LIST, TODO, DEADLINE, EVENT
    }

    public static void main(String[] args) {
        printInitialMsg();
        Scanner in = initialiseInput();
        TaskList taskList = new TaskList();

        inputCommand command;
        do {
            String input = takeInput(in);
            command = processInput(taskList, input);
            outputReaction(command, input, taskList);
        } while (command != inputCommand.BYE);
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

    private static Scanner initialiseInput() {
        return new Scanner(System.in);
    }

    private static String takeInput(Scanner in) {
        return in.nextLine();
    }

    private static inputCommand processInput(TaskList taskList, String input) {
        switch (getCommand(input)) {
        case ADD:
            taskList.addTask(input, inputCommand.ADD);
            break;
        case TODO:
            taskList.addTask(input, inputCommand.TODO);
            break;
        case DEADLINE:
            taskList.addTask(input, inputCommand.DEADLINE);
            break;
        case EVENT:
            taskList.addTask(input, inputCommand.EVENT);
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

    private static void outputReaction(inputCommand command, String input, TaskList taskList) {
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

    private static inputCommand getCommand(String input) {
        if (input.equals("list")) {
            return inputCommand.LIST;
        }
        else if (input.equals("bye")) {
            return inputCommand.BYE;
        }
        else if (input.startsWith("done ")) {
            return inputCommand.DONE;
        }
        else if (input.startsWith("todo ")) {
            return inputCommand.TODO;
        }
        else if (input.startsWith("deadline ")) {
            return inputCommand.DEADLINE;
        }
        else if (input.startsWith("event ")) {
            return inputCommand.EVENT;
        }
        else {
            return inputCommand.ADD;
        }
    }

    private static int getTaskNum(String input) {
        return Integer.parseInt(input.replace("done ", ""));
    }
}
