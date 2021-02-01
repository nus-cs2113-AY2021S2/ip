import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Task[] tasks = new Task[MAX_TASKS];

    public static void main(String[] args) {
        Menu.printGreeting();
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
            executeList();
            break;
        case "done":
            executeDone(commandArgs);
            break;
        case "bye":
            executeExit();
            break;
        case "todo":
        case "deadline":
        case "event":
        default:
            // handle other user inputs as tasks for now
            executeAdd(commandType, commandArgs);
            break;
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

    private static void executeList() {
        String outputText;
        outputText = "Here are the tasks in your list:";
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            String taskNumber = (i + 1) + ".";
            outputText += System.lineSeparator() +
                    " " +
                    taskNumber + " " +
                    tasks[i].toString();
        }
        Menu.printText(outputText);
    }

    private static void executeDone(String taskNumberString) {
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
        Menu.printText(outputText);
    }

    private static void executeAdd(String commandType, String commandArgs) {
        String outputText = "Got it. I've added this task:" + System.lineSeparator();
        Task t;
        switch (commandType) {
        case "todo":
            String todoArgs = commandArgs;
            t = new Todo(todoArgs);
            break;
        case "deadline":
            String[] deadlineArgs = commandArgs.split("\\s+/by\\s+",2);
            t = new Deadline(deadlineArgs[0], deadlineArgs[1]);
            break;
        case "event":
            String[] eventArgs = commandArgs.split("\\s+/at\\s+", 2);
            t = new Event(eventArgs[0], eventArgs[1]);
            break;
        default:
            String taskArgs = commandArgs;
            t = new Task(taskArgs);
            break;
        }
        tasks[Task.getNumberOfTasks() - 1] = t;
        outputText += "\t" + t + System.lineSeparator();
        outputText += "Now you have " + Task.getNumberOfTasks() + " tasks in the list.";
        Menu.printText(outputText);
    }

    private static void executeExit() {
        Menu.printBye();
        System.exit(0);
    }
}
