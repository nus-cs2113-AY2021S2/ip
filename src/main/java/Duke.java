import java.util.Scanner;

public class Duke {
    static final String COMMANDS = "Commands:\n    todo taskName\n    deadline deadlineName /by time\n" 
            + "    event eventName /at time\n    list\n    done taskNumber\n    help\n    bye\n";

    public static void main(String[] args) {
        displayWelcomeMessage();
        int taskCount = 0;
        Task[] taskList = new Task[100];
        inputAndExecuteCommand(taskCount, taskList);
    }

    private static void inputAndExecuteCommand(int taskCount, Task[] taskList) {
        String line;
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            line = scanner.nextLine();
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
                scanner.close();
                System.exit(0);
            case "help":
                printWithBorder(COMMANDS);
                break;
            case "list":
                listAllTasks(taskList);
                break;
            case "done":
                markTaskAsDone(taskList, commandArg);
                break;
            case "todo":
                taskCount = addTodo(taskCount, taskList, commandArg);
                break;
            case "deadline":
                taskCount = addDeadline(taskCount, taskList, commandArg);
                break;
            case "event":
                taskCount = addEvent(taskCount, taskList, commandArg);
                break;
            default:
                displayInvalidCommandResponse();
                break;
            }
        }
    }

    private static int addEvent(int taskCount, Task[] taskList, String commandArg) {
        String[] taskDescriptionAndAt = commandArg.split(" /at ", 2);
        String description = taskDescriptionAndAt[0];
        String at = taskDescriptionAndAt[1];
        Event task = new Event(description, at);
        taskList[taskCount] = task;
        taskCount += 1;
        printWithBorder("Alrighty! I have added this new Event:\n    " + task.toString() + "\nYou now have "
        + Integer.toString(taskCount) + " tasks in the list.");
        return taskCount;
    }

    private static int addDeadline(int taskCount, Task[] taskList, String commandArg) {
        String[] taskDescriptionAndBy = commandArg.split(" /by ", 2);
        String description = taskDescriptionAndBy[0];
        String by = taskDescriptionAndBy[1];
        Deadline task = new Deadline(description, by);
        taskList[taskCount] = task;
        taskCount += 1;
        printWithBorder("Alrighty! I have added this new Deadline:\n    " + task.toString() + "\nYou now have "
        + Integer.toString(taskCount) + " tasks in the list.");
        return taskCount;
    }

    private static void displayInvalidCommandResponse() {
        String invalidCommandResponse = "Invalid command!\n" + COMMANDS;
        printWithBorder(invalidCommandResponse);
    }

    private static int addTodo(int taskCount, Task[] taskList, String commandArg) {
        Todo task = new Todo(commandArg);
        taskList[taskCount] = task;
        taskCount += 1;
        printWithBorder("Alrighty! I have added this new Todo:\n    " + task.toString() + "\nYou now have "
        + Integer.toString(taskCount) + " tasks in the list.");
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
            stringToPrint += ("\n    " + Integer.toString(count) + ". " + task.toString());
            count += 1;
        }
        printWithBorder(stringToPrint);
    }

    private static void displayWelcomeMessage() {
        String logo = "         __    _    _              ____        __           \n"
                +"        / /_  (_)  (_)___ ___     / __ \\__  __/ /_____      \n"
                +"       / __ \\/ /  / / __ `__ \\   / / / / / / / //_/ _ \\     \n"
                +"      / / / / /  / / / / / / /  / /_/ / /_/ / ,< /  __/     \n"
                +"     /_/ /_/_/  /_/_/ /_/ /_/  /_____/\\__,_/_/|_|\\___/     \n";
        System.out.print(logo + "\n");
        System.out.print("What do you have to do today?\n");
        System.out.print(COMMANDS + "\n");
    }

    public static void printWithBorder(String line) {
        System.out.print("___________________________________________________\n");
        System.out.print(line + "\n");
        System.out.print("___________________________________________________\n");
    }
}