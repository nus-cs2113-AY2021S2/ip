import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
        loadHistory();
        inputAndExecuteCommand();
        saveHistory();
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

    private static void loadHistory() {
        String home = System.getProperty("user.dir");
        createDirectory(home);
        loadDataFile(home);
    }

    private static void createDirectory(String home) {
        Path path = Paths.get(home, "data");
        try {
            Files.createDirectory(path);
        } catch (FileAlreadyExistsException e) {
            // do nothing
        } catch (Exception e) {
            String errorMessage = "ERROR creating directory!\n" + e.getLocalizedMessage();
            printWithBorder(errorMessage);
        }
    }

    private static void loadDataFile(String home) {
        Path path = Paths.get(home, "data", "duke.txt");
        if (Files.notExists(path)) {
            return;
        }
        try {
            List<String> data = Files.readAllLines(path);
            for (String line : data) {
                loadTask(line);
            }
        } catch (Exception e) {
            String errorMessage = "ERROR retrieving data!\n" + e.getLocalizedMessage();
            printWithBorder(errorMessage);
        }
    }

    private static void loadTask(String line) {
        String[] tokens = line.split("~");
        String taskType = tokens[0];
        String isDone = tokens[1];
        String description = tokens[2];
        Task task = new Task(description);
        switch (taskType) {
        case "Todo":
            task = new Todo(description);
            break;
        case "Deadline":
            String by = tokens[3];
            task = new Deadline(description, by);
            break;
        case "Event":
            String at = tokens[3];
            task = new Event(description, at);
            break;
        }
        if (isDone == "1") {
            task.setIsDone();
        }
        taskList.add(task);
        taskCount += 1;
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

    private static void saveHistory() {
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, "data", "duke.txt");
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            ArrayList<String> listOfTaskDetails = getListOfTaskDetails();
            Files.write(path, listOfTaskDetails);
        } catch (IOException e) {
            String errorMessage = "ERROR saving data!\n" + e.getMessage();
            printWithBorder(errorMessage);
        }
    }

    private static ArrayList<String> getListOfTaskDetails() {
        ArrayList<String> listOfTaskDetails = new ArrayList<String>();
        for (Task task : taskList) {
            if (task == null){
                break;
            }
            String taskType = task.getClass().getSimpleName();
            String isDone;
            if (task.isDone()) {
                isDone = "1";
            } else {
                isDone = "0";
            }
            String taskDescription = task.getDescription();
            String taskDetails = taskType + "~" + isDone + "~" + taskDescription;
            taskDetails = getTaskDetails(task, taskDetails);
            listOfTaskDetails.add(taskDetails);
        }
        return listOfTaskDetails;
    }

    private static String getTaskDetails(Task task, String taskDetails) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String by = deadline.getBy();
            taskDetails += "~" + by;
        }
        if (task instanceof Event) {
            Event event = (Event) task;
            String at = event.getAt();
            taskDetails += "~" + at;
        }
        return taskDetails;
    }

    private static void displayExitMessage() {
        String exitMessage = "Sad to see you go! ): See you soon!";
        printWithBorder(exitMessage);
    }
}