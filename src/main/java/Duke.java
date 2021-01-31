import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Task[] tasks = new Task[MAX_TASKS];

    public static void main(String[] args) {
        Menu.greet();
        while (true) {
            String userInput = getUserInput();
            executeUserCommand(userInput);
        }
    }

    private static void executeUserCommand(String userInput) {
        String[] commandAndArgs = getCommandAndArgs(userInput);
        String commandType = commandAndArgs[0];
        String commandArgs = commandAndArgs[1];
        switch (commandType) {
        case "list":
            handleList();
            break;
        case "done":
            handleDone(commandArgs);
            break;
        case "bye":
            handleExit();
            break;
        default:
            handleAdd(userInput);
        }
    }

    private static String getUserInput() {
        String userInput;
        userInput = SCANNER.nextLine();
        return userInput;
    }

    private static String[] getCommandAndArgs(String userInput) {
        String[] split = userInput.split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0], "" };
    }

    private static void handleList() {
        String outputText;
        outputText = "Here are the tasks in your list: ";
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            String taskNumber = (i + 1) + ".";
            outputText += System.lineSeparator() +
                    " " +
                    taskNumber + " " +
                    tasks[i].toString();
        }
        Menu.echo(outputText);
    }

    private static void handleDone(String taskNumberString) {
        int taskNumber = Integer.parseInt(taskNumberString);
        String outputText;
        if (taskNumber < 1 || taskNumber > Task.getNumberOfTasks()) {
            outputText = "Task number invalid!";
        } else {
            Task task = tasks[taskNumber - 1];
            if (!task.isDone) {
                task.setDone(true);
                outputText = "Nice! I've marked this task as done:" +
                        System.lineSeparator() +
                        "\t" +
                        task.toString();
            } else {
                outputText = "Task already marked as done!";
            }
        }
        Menu.echo(outputText);
    }

    private static void handleAdd(String taskDescription) {
        String outputText = " added: ";
        Task t = new Task(taskDescription);
        tasks[Task.getNumberOfTasks() - 1] = t;
        outputText += taskDescription;
        Menu.echo(outputText);
    }

    private static void handleExit() {
        Menu.bye();
        System.exit(0);
    }
}
