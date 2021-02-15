import java.util.ArrayList;
import java.util.Scanner;
import duke.task.*;
import duke.exception.*;

public class Duke {
    static int taskCount = 0;
    static ArrayList<Task> taskList = new ArrayList<Task>();
    static final String COMMANDS = "Commands:\n    todo taskName\n    deadline deadlineName /by time\n" 
            + "    event eventName /at time\n    list\n    done taskNumber\n    delete taskNumber\n   help\n    bye\n";

    public static void main(String[] args) {
        displayWelcomeMessage();
        inputAndExecuteCommand();
        displayExitMessage();
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
        System.out.print("_______________________________________________________________________________\n");
        System.out.print(line + "\n");
        System.out.print("_______________________________________________________________________________\n");
    }

    private static void inputAndExecuteCommand() {
        String line;
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            line = scanner.nextLine();
            String[] commandTypeAndArg = line.split(" ", 2);
            String commandType = commandTypeAndArg[0].trim();
            String commandArg = null;
            if (commandTypeAndArg.length > 1) {
                commandArg = commandTypeAndArg[1].trim();
            }

            if (commandType.equals("bye")) {
                scanner.close();
                return;
            }
            
            try {
                executeCommand(commandType, commandArg);
            } catch (NullCommandArgException | InvalidCommandTimeException
            | InvalidCommandException | InvalidTaskNumberException e) {
                printWithBorder(e.getMessage());
                continue;
            }
        }
    }
    
    private static void executeCommand(String commandType, String commandArg) throws NullCommandArgException,
    InvalidCommandTimeException, InvalidCommandException, InvalidTaskNumberException {
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
        case "delete":
            deleteTask(commandArg);
            break;
        default:
            throw new InvalidCommandException(commandType);
        }
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

    private static void markTaskAsDone(String commandArg) throws NullCommandArgException, InvalidTaskNumberException {
        checkNullArgs("done", commandArg);
        int taskNumber = checkNumberValidity(commandArg);
        
        Task task = taskList.get(taskNumber - 1);
        task.setIsDone();
        String taskSuccessfullyMarkedDoneMessage = "Very nice! I've marked this task as done:\n    " 
                + task.toString();
        printWithBorder(taskSuccessfullyMarkedDoneMessage);
    }

    private static int checkNumberValidity(String commandArg) throws InvalidTaskNumberException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(commandArg);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(commandArg);
        }
        
        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new InvalidTaskNumberException(taskNumber);
        }
        return taskNumber;
    }

    private static void addTodo(String commandArg) throws NullCommandArgException {
        checkNullArgs("todo", commandArg);
        Todo task = new Todo(commandArg);
        addTaskToListAndPrintMessage(task);
    }

    private static void addDeadline(String commandArg) throws NullCommandArgException, InvalidCommandTimeException {
        checkNullArgs("deadline", commandArg);
        String[] taskDescriptionAndBy = splitCommandArg("deadline", commandArg);  
        String description = taskDescriptionAndBy[0];
        String by = taskDescriptionAndBy[1];
        Deadline task = new Deadline(description, by);
        addTaskToListAndPrintMessage(task);
    }

    private static void addEvent(String commandArg) throws NullCommandArgException, InvalidCommandTimeException {
        checkNullArgs("event", commandArg);
        String[] taskDescriptionAndAt = splitCommandArg("event", commandArg);       
        String description = taskDescriptionAndAt[0];
        String at = taskDescriptionAndAt[1];
        Event task = new Event(description, at);
        addTaskToListAndPrintMessage(task);
    }

    private static String[] splitCommandArg(String commandType, String commandArg) throws InvalidCommandTimeException {
        String[] taskDescriptionAndTime;
        String delimiter = null;
        switch (commandType) {
        case "deadline":
            delimiter = "/by";
            break;
        case "event":
            delimiter = "/at";
            break;
        }
        taskDescriptionAndTime = commandArg.split(delimiter, 2);
        if (taskDescriptionAndTime.length == 1 || taskDescriptionAndTime[1].equals("")) {
            throw new InvalidCommandTimeException(commandType);
        }
        taskDescriptionAndTime[0] = taskDescriptionAndTime[0].trim();
        taskDescriptionAndTime[1] = taskDescriptionAndTime[1].trim();

        return taskDescriptionAndTime;
    }

    private static void checkNullArgs(String commandType, String commandArg) throws NullCommandArgException {
        if (commandArg == null) {
            throw new NullCommandArgException(commandType);
        }
    }

    private static void addTaskToListAndPrintMessage(Task task) {
        taskList.add(task);
        taskCount += 1;
        String className = task.getClass().getSimpleName();
        String taskSuccessfullyAddedMessage = "Alrighty! I have added this new " + className + ":\n    "
                + task.toString() + "\nYou now have " + Integer.toString(taskCount) + " tasks in the list.";
        printWithBorder(taskSuccessfullyAddedMessage);
    }    

    private static void deleteTask(String commandArg) throws NullCommandArgException, InvalidTaskNumberException {
        checkNullArgs("delete", commandArg);
        int taskNumber = checkNumberValidity(commandArg);

        Task task = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        taskCount -= 1;
        String taskSuccessfullyDeletedMessage = "Yay! I've successfuly deleted this task:\n    " 
                + task.toString();
        printWithBorder(taskSuccessfullyDeletedMessage);
    }

    private static void displayExitMessage() {
        String exitMessage = "Sad to see you go! ): See you soon!";
        printWithBorder(exitMessage);
    }
}