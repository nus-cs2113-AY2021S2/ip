import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        displayWelcomeMessage();
        int taskCount = 0;
        Task[] taskList = new Task[100];
        inputAndExecuteCommand(taskCount, taskList);
    }

    private static void inputAndExecuteCommand(int taskCount, Task[] taskList) {
        String line;
        
        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] commandTypeAndArg = line.split(" ", 2);
            String commandType = commandTypeAndArg[0];
            String commandArg = null;
            if (commandTypeAndArg.length > 1) {
                commandArg = commandTypeAndArg[1];
            }

            switch (commandType) {
            case "bye":
                String exitMessage = "Sad to see you go! ): See you soon!";
                printWithBorder(exitMessage);
                System.exit(0);
            case "list":
                listAllTasks(taskList);
                break;
            case "done":
                markTaskAsDone(taskList, commandArg);
                break;
            case "add":
                taskCount = addTask(taskCount, taskList, commandArg);
                break;
            default:
                displayInvalidCommandResponse();
            }
        }
    }

    private static void displayInvalidCommandResponse() {
        String invalidCommandResponse = "Invalid command!\nCommands: 'add', 'list', 'done'\n";
        printWithBorder(invalidCommandResponse);
    }

    private static int addTask(int taskCount, Task[] taskList, String commandArg) {
        taskList[taskCount] = new Task(commandArg);
        taskCount += 1;
        printWithBorder("I have added: " + commandArg);
        return taskCount;
    }

    private static void markTaskAsDone(Task[] taskList, String commandArg) {
        int taskNumber = Integer.parseInt(commandArg);
        Task task = taskList[taskNumber - 1];
        task.setIsDone();
        printWithBorder("Very nice! I've marked this task as done:\n" + "[" + task.getStatusIcon() + "] "
                + task.getDescription());
    }

    private static void listAllTasks(Task[] taskList) {
        int count = 1;
        String stringToPrint = "Here are the tasks in your list:";
        for (Task task : taskList) {
            if (task == null) {
                break;
            }
            stringToPrint += ("\n" + Integer.toString(count) + ": [" + task.getStatusIcon() + "] "
                    + task.getDescription());
            count += 1;
        }
        printWithBorder(stringToPrint);
    }

    private static void displayWelcomeMessage() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.print("Hello from\n" + logo + "\n");
        System.out.print("What do you have to do today?\n");
        System.out.print("Commands: 'add', 'list', 'done'\n");
    }

    public static void printWithBorder(String line) {
        System.out.print("___________________________________________________\n");
        System.out.print(line + "\n");
        System.out.print("___________________________________________________\n");
    }
}