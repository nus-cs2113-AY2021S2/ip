package duke;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final char INPUT_COMMENT_MARKER = '#';
    private static final int CAPACITY = 99;
    private static TaskManager tasks;
    private static String filePath;


    public static void initTaskManager() {
         tasks = new TaskManager(CAPACITY);
    }

    public static void showHello() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm duke.Duke, your truly daily helper");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

    }

    public static void showBye() {
        showExecuteResult("Bye. Hope to see you again soon!");
    }

    public static void showExecuteResult(String result) {
        System.out.println("____________________________________________________________");
        System.out.println(result);
        System.out.println("____________________________________________________________");
    }

    public static void showAddResult(Task t) {
        Duke.showExecuteResult("Got it. I've added this task:\n" + t + "\nNow you have " + (tasks.getNumOfTasks()) + " tasks in the list.");
    }

    private static String getUserInput() {
        String inputLine = SCANNER.nextLine();
        // silently consume all blank and comment lines
        while (inputLine.trim().isEmpty() || inputLine.trim().charAt(0) == INPUT_COMMENT_MARKER) {
            inputLine = SCANNER.nextLine();
        }
        return inputLine;
    }

    public static CommandType getCommandType(String userInputString) {
        CommandType commandType;
        if(userInputString.equalsIgnoreCase("LIST")) {
            commandType = CommandType.LIST;
        } else if (userInputString.toUpperCase().matches("^(DONE).*$")) {
            commandType = CommandType.DONE;
        } else if (userInputString.toUpperCase().matches("^(TODO).*$")) {
            commandType = CommandType.TODO;
        } else if (userInputString.toUpperCase().matches("^(DEADLINE).*$")) {
            commandType = CommandType.DEADLINE;
        } else if (userInputString.toUpperCase().matches("^(EVENT).*$")) {
            commandType = CommandType.EVENT;
        } else if (userInputString.equalsIgnoreCase("BYE")) {
            commandType = CommandType.EXIT;
        } else {
            commandType = CommandType.UNDEFINED;
        }
        return commandType;
    }

    public static void showMessageForInvalidCommandInput() {
        showExecuteResult("OOPS!!! I'm sorry, but I don't know what that means :-(!");
    }

    public static void executeAddTodo(String userCommand) throws EmptyDescriptionException, IOException {
        String[] typeContent = userCommand.split("[Tt][Oo][Dd][Oo]",2);
        if (typeContent[1].equals("")) {
            throw new EmptyDescriptionException(CommandType.TODO);
        }

        Todo t = tasks.addTodo(typeContent[1].trim());
        showAddResult(t);
        tasks.writeToTxt(filePath);
    }

    public static void executeAddDeadline(String userCommand) throws EmptyDescriptionException, IOException {
        try {
            String[] typeContentBy = userCommand.trim().split("[Dd][Ee][Aa][Dd][Ll][Ii][Nn][Ee]", 2);
            String[] contentBy = typeContentBy[1].trim().split("/[Bb][Yy]", 2);
            if (contentBy[0].trim().equals("") || contentBy[1].trim().equals("")) {
                throw new EmptyDescriptionException(CommandType.DEADLINE);
            }
            Deadline d = tasks.addDeadline(contentBy[0].trim(), contentBy[1].trim());
            showAddResult(d);
            tasks.writeToTxt(filePath);
        } catch (ArrayIndexOutOfBoundsException e) {
            showExecuteResult("OOPS!!! No /by founded in the command");

        }

    }

    public static void executeAddEvent(String userCommand) throws EmptyDescriptionException, IOException {
        try {
            String[] typeContentAt= userCommand.trim().split("[Ee][Vv][Ee][Nn][Tt]", 2);
            String[] contentAt = typeContentAt[1].trim().split("/[Aa][Tt]", 2);
            if (contentAt[0].trim().equals("") || contentAt[1].trim().equals("")) {
                throw new EmptyDescriptionException(CommandType.EVENT);
            }
            Event e = tasks.addEvent(contentAt[0].trim(), contentAt[1].trim());
            showAddResult(e);
            tasks.writeToTxt(filePath);
        } catch (IndexOutOfBoundsException e){
            showExecuteResult("OOPS!!! No /at founded in the command");
        }

    }

    public static void executeList(String userCommand) {
        tasks.listAllTasks();
    }

    public static void executeDone(String userCommand) throws EmptyDescriptionException, IOException {
        try{
            int taskIndexShow = Integer.parseInt(userCommand.replaceAll("[^0-9]", ""));
            if(taskIndexShow <= 0 || taskIndexShow > tasks.getNumOfTasks()) {
                throw new EmptyDescriptionException(CommandType.DONE);
            }
            tasks.markTaskDone(taskIndexShow);
            tasks.writeToTxt(filePath);
        } catch (NumberFormatException e) {
            throw new EmptyDescriptionException(CommandType.DONE);
        }
    }

    private static void executeExitProgramRequest() {
        showBye();
        System.exit(0);
    }

    public static void executeCommand(String userInputString) throws IOException {
        CommandType type = getCommandType(userInputString);
        try {
            switch (type) {
            case TODO:
                executeAddTodo(userInputString);
                return;
            case DEADLINE:
                executeAddDeadline(userInputString);
                return;
            case EVENT:
                executeAddEvent(userInputString);
                return;
            case LIST:
                executeList(userInputString);
                return;
            case DONE:
                executeDone(userInputString);
                return;
            case EXIT:
                executeExitProgramRequest();
                return;
            default:
                showMessageForInvalidCommandInput();
                return;
            }
        } catch (EmptyDescriptionException e) {
            e.showMessage();
            return;
        }

    }

    public static void main(String[] args) throws IOException {
        File f = loadFile();
        initTaskManager();
        loadData(f);
        showHello();
        while (true) {
            String userCommand = getUserInput();
            executeCommand(userCommand.trim());
        }


    }

    private static void loadData(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()){
            tasks.addFromTXT(s.nextLine());
        }
        s.close();
    }

    private static File loadFile() {
        String localDir = System.getProperty("user.dir");
        Path dirPath = Paths.get(localDir, "data");

        if(!Files.exists(dirPath)) {
            try {
                Files.createDirectory(dirPath);
            } catch (IOException e) {
                System.err.println("Failed to create directory 'data'!" + e.getMessage());
            }
        }

        filePath = localDir + File.separator + "data" + File.separator + "duke.txt";
        File f = new File(filePath);

        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f;
    }
}
