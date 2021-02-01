import java.util.Scanner;

public class Duke {
    static int taskCount = 0;
    static Task[] taskList = new Task[100];
    static final String COMMANDS = "Commands:\n    todo taskName\n    deadline deadlineName /by time\n" 
            + "    event eventName /at time\n    list\n    done taskNumber\n    help\n    bye\n";

    public static void main(String[] args) {
        displayWelcomeMessage();
        inputAndExecuteCommand();
        displayExitMessage();
    }

    private static void displayExitMessage() {
        String exitMessage = "Sad to see you go! ): See you soon!";
        printWithBorder(exitMessage);
    }

    private static void inputAndExecuteCommand() {
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

            if (commandType.equals("bye")) {
                scanner.close();
                return;
            }
            executeCommand(commandType, commandArg);
        }
    }
    
    private static void executeCommand(String commandType, String commandArg) {
        switch (commandType) {
        case "help":
            printWithBorder(COMMANDS);
            break;
        case "list":
            listAllTasks();
            break;
        case "done":
            markTaskAsDone(commandArg);
            break;
        case "todo":
            addTodo(commandArg);
            break;
        case "deadline":
            addDeadline(commandArg);
            break;
        case "event":
            addEvent(commandArg);
            break;
        default:
            displayInvalidCommandResponse();
            break;
        }
    }

    private static void addTodo(String commandArg) {
        Todo task = new Todo(commandArg);
        addTaskToListAndPrintMessage(task);
    }

    private static void addEvent(String commandArg) {
        String[] taskDescriptionAndAt = commandArg.split(" /at ", 2);
        String description = taskDescriptionAndAt[0];
        String at = taskDescriptionAndAt[1];
        Event task = new Event(description, at);
        addTaskToListAndPrintMessage(task);
    }

    private static void addDeadline(String commandArg) {
        String[] taskDescriptionAndBy = commandArg.split(" /by ", 2);
        String description = taskDescriptionAndBy[0];
        String by = taskDescriptionAndBy[1];
        Deadline task = new Deadline(description, by);
        addTaskToListAndPrintMessage(task);
    }

    private static void addTaskToListAndPrintMessage(Task task) {
        taskList[taskCount] = task;
        taskCount += 1;
        String className = task.getClass().getSimpleName();
        String taskSuccessfullyAddedMessage = "Alrighty! I have added this new " + className + ":\n    "
                + task.toString() + "\nYou now have " + Integer.toString(taskCount) + " tasks in the list.";
        printWithBorder(taskSuccessfullyAddedMessage);
    }

    private static void displayInvalidCommandResponse() {
        String invalidCommandResponse = "Invalid command!\n" + COMMANDS;
        printWithBorder(invalidCommandResponse);
    }

    private static void markTaskAsDone(String commandArg) {
        int taskNumber = Integer.parseInt(commandArg);
        Task task = taskList[taskNumber - 1];
        task.setIsDone();
        String taskSuccessfullyMarkedDoneMessage = "Very nice! I've marked this task as done:\n    " 
                + task.toString();
        printWithBorder(taskSuccessfullyMarkedDoneMessage);
    }

    private static void listAllTasks() {
        int count = 1;
        String listOfTasksString = "Here are the tasks in your list:";
        for (Task task : taskList) {
            if (task == null) {
                break;
            }
            listOfTasksString += ("\n    " + Integer.toString(count) + ". " + task.toString());
            count += 1;
        }
        printWithBorder(listOfTasksString);
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