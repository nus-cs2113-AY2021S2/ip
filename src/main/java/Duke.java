import java.util.Scanner;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.Ui;
import duke.exception.EmptyCommandArgException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidCommandTimeException;
import duke.exception.InvalidTaskNumberException;

public class Duke {
    public static Ui ui;
    public static TaskList taskList;
    private static Storage storage;
    private static String home;

    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(ui);
        home = System.getProperty("user.dir");
    }

    public static void main(String[] args) {
        new Duke();
        ui.displayWelcomeMessage();
        storage.loadHistory(home, taskList);
        inputAndExecuteCommand();
        storage.saveHistory(home, taskList);
        ui.displayExitMessage();
    }

    public static void inputAndExecuteCommand() {
        String line;
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            line = scanner.nextLine();
            String[] commandTypeAndArg = line.split(" ", 2);
            String commandType = commandTypeAndArg[0].trim();
            String commandArg = "";
            if (commandTypeAndArg.length > 1) {
                commandArg = commandTypeAndArg[1].trim();
            }

            if (commandType.equals("bye")) {
                scanner.close();
                return;
            }
            
            try {
                executeCommand(commandType, commandArg);
            } catch (EmptyCommandArgException | InvalidCommandTimeException
                    | InvalidCommandException | InvalidTaskNumberException e) {
                ui.printErrorMessage(e);
                continue;
            }
        }
    }
    
    public static void executeCommand(String commandType, String commandArg) throws EmptyCommandArgException,
    InvalidCommandTimeException, InvalidCommandException, InvalidTaskNumberException {
        switch (commandType) {
        case "help":
            ui.printHelpMessage();
            break;
        case "list":
            ui.listAllTasks(taskList);
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

    public static String[] splitCommandArg(String commandType, String commandArg) throws InvalidCommandTimeException {
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

    public static void markTaskAsDone(String commandArg) throws EmptyCommandArgException, InvalidTaskNumberException {
        if (isEmptyArgument(commandArg)) {
            throw new EmptyCommandArgException("done");
        }
        int taskNumber = getTaskNumber(commandArg);
        Task task = taskList.getTask(taskNumber - 1);
        task.setIsDone();
        ui.printSuccessfullyMarkedDoneMessage(task);
    }

    public static int getTaskNumber(String commandArg) throws InvalidTaskNumberException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(commandArg);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(commandArg);
        }

        if (taskNumber < 1 || taskNumber > taskList.getListSize()) {
            throw new InvalidTaskNumberException(taskNumber);
        }
        return taskNumber;
    }

    public static void addTodo(String commandArg) throws EmptyCommandArgException {
        if (isEmptyArgument(commandArg)) {
            throw new EmptyCommandArgException("todo");
        }
        Todo task = new Todo(commandArg);
        taskList.addTask(task);
        ui.printTaskSuccessfullyAddedMessage(task, taskList.getListSize());
    }

    public static void addDeadline(String commandArg) throws EmptyCommandArgException, InvalidCommandTimeException {
        if (isEmptyArgument(commandArg)) {
            throw new EmptyCommandArgException("deadline");
        }
        String[] taskDescriptionAndBy = splitCommandArg("deadline", commandArg);  
        String description = taskDescriptionAndBy[0];
        String by = taskDescriptionAndBy[1];
        Deadline task = new Deadline(description, by);
        taskList.addTask(task);
        ui.printTaskSuccessfullyAddedMessage(task, taskList.getListSize());
    }

    public static void addEvent(String commandArg) throws EmptyCommandArgException, InvalidCommandTimeException {
        if (isEmptyArgument(commandArg)) {
            throw new EmptyCommandArgException("event");
        }
        String[] taskDescriptionAndAt = splitCommandArg("event", commandArg);       
        String description = taskDescriptionAndAt[0];
        String at = taskDescriptionAndAt[1];
        Event task = new Event(description, at);
        taskList.addTask(task);
        ui.printTaskSuccessfullyAddedMessage(task, taskList.getListSize());
    }

    public static void deleteTask(String commandArg) throws EmptyCommandArgException, InvalidTaskNumberException {
        if (isEmptyArgument(commandArg)) {
            throw new EmptyCommandArgException("delete");
        }
        int taskNumber = getTaskNumber(commandArg);
        Task task = taskList.getTask(taskNumber - 1);
        taskList.deleteTask(taskNumber - 1);
        ui.printTaskSuccessfullyDeletedMessage(task);
    }

    public static boolean isEmptyArgument(String commandArg) {
        return commandArg.length() == 0;
    }
}