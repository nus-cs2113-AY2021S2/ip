import java.util.Scanner;

public class Duke {
    public enum inputCommand {
        ADD, BYE, LIST, DONE, ERROR
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
            taskList.addTask(input);
            return inputCommand.ADD;
        case BYE:
            return inputCommand.BYE;
        case LIST:
            return inputCommand.LIST;
        case DONE:
            //Retrieve task number from input
            int taskNum = getTaskNum(input);
            taskList.finishTask(taskNum - 1);
            return inputCommand.DONE;
        default:
            return inputCommand.ERROR;
        }
    }

    private static void outputReaction(inputCommand command, String input, TaskList taskList) {
        switch (command) {
        case ADD:
            System.out.println("Added: " + input);
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
        else {
            return inputCommand.ADD;
        }
    }

    private static int getTaskNum(String input) {
        return Integer.parseInt(input.replace("done ", ""));
    }
}
