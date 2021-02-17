import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Bob {
    public static final String LINE_STRING = "____________________________________________________________\n";
    public static final String DATA_DELIMITER = " @_@ ";
    public static final String DATA_DIRECTORY = "data";
    public static final String SAVE_FILE_NAME = "duke.txt";
    public static final String DIRECTORY_HOME = System.getProperty("user.home");
    public static TaskList taskList = new TaskList();


    public static void main(String[] args) {
        welcomeMessage();
        loadFile();
        scanInput();
        goodbyeMessage();
    }

    private static void welcomeMessage() {
        String welcome = LINE_STRING +
                " Hello! I'm Bob 😀\n" +
                " If you need anything hit me up fam 😌\n" +
                LINE_STRING;
        System.out.print(welcome);
    }

    private static void loadFile() {
        createDataDirectory();
        openFile();
    }

    private static void createDataDirectory() {
        Path path = Paths.get(DIRECTORY_HOME, "data");
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            // printIOException(); TODO: figure out why IO Exception is triggered here
        }
    }

    private static void openFile() {
        Path path = Paths.get(DIRECTORY_HOME, DATA_DIRECTORY, SAVE_FILE_NAME);
        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException  e) {
            loadDataFile(path);
        } catch (IOException e) {
            printIOException();
        }
    }

    private static void loadDataFile(Path path) {
        try {
            List<String> taskStrings = Files.readAllLines(path);
            for (String taskString: taskStrings) {
                loadTask(taskString);
            }
        } catch (IOException e) {
            printIOException();
        } catch (NoSuchMethodException e) {
            printNoSuchMethod();
        } catch (NumberFormatException e) {
            printNumberFormatException();
        }
    }

    private static void loadTask(String taskString) throws NoSuchMethodException, NumberFormatException{
        String[] taskStringArray = taskString.split(DATA_DELIMITER);
        Command command = getCommandType(taskStringArray[0]);
        String isDone = taskStringArray[1];
        String label = taskStringArray[2];
        String time = taskStringArray[3];

        switch (command) {
        case TODO:
            taskList.addToList(new Todo(label),false);
            break;
        case DEADLINE:
            taskList.addToList(new Deadline(label, time),false);
            break;
        case EVENT:
            taskList.addToList(new Event(label, time),false);
            break;
        }

        if (Integer.parseInt(isDone) == 1) {
            taskList.completeTask(taskList.getSize(), false);
        }
    }

    private static void saveData() {
        Path path = Paths.get(DIRECTORY_HOME, DATA_DIRECTORY, SAVE_FILE_NAME);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            List<String> taskStrings = taskList.saveTaskList();
            Files.write(path, taskStrings);
        } catch (IOException e) {
            printIOException();
        }
    }

    private static void scanInput() {
        Scanner in = new Scanner(System.in);
        scanLoop(in);
    }

    private static void goodbyeMessage() {
        String goodbye = LINE_STRING + " Chao 👌\n" + LINE_STRING;
        System.out.print(goodbye);
    }

    /**
     * Scanner loop until bye command
     */
    private static void scanLoop(Scanner in) {
        boolean isScanning = true;
        String inputString;
        Command commandType = null;

        while (isScanning) {
            try {
                inputString = in.nextLine();
                commandType = getCommandType(inputString);
                isScanning = scanSwitch(inputString, commandType);
                saveData();
            } catch (java.util.InputMismatchException e) {
                printInputMismatch();
            } catch (NoSuchMethodException  e) {
                printNoSuchMethod();
            } catch (NoCommandLabelException e) {
                printNoCommandLabel(commandType);
            } catch (NoCommandFormatException e) {
                printNoCommandFormat(commandType);
            }
        }
    }

    private static void printNumberFormatException() {
        String exceptionMessage = LINE_STRING +
                " 😥 Number format exception encountered! Input may be corrupted\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    private static void printIOException() {
        String exceptionMessage = LINE_STRING +
                " 😥 IO issue encountered! Unable to read file\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    private static void printInputMismatch() {
        String exceptionMessage = LINE_STRING +
                " 😥 There is some issue with getting you input\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    private static void printNoSuchMethod() {
        String exceptionMessage = LINE_STRING +
                " 😥 I don't quite get what the command means\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    private static void printNoCommandLabel(Command commandType) {
        String commandName = commandType.name().toLowerCase();
        String exceptionMessage = LINE_STRING +
                " 😥 You gotta tell me what is the task for " + commandName + "\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    private static void printNoCommandFormat(Command commandType) {
        String commandName = commandType.name().toLowerCase();
        String exceptionMessage = LINE_STRING +
                " 😥 You gotta use the time marker for " + commandName + "\n" +
                "and the time which it happens\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    /**
     * String before first space taken as command rest as args
     */
    private static boolean scanSwitch(String inputString, Command commandType) throws NoSuchMethodException, NoCommandLabelException, NoCommandFormatException {
        String[] inputStringArr = inputString.split(" ");
        boolean isScanning = true;

        switch (commandType) {
        case LIST:
            taskList.printList();
            break;
        case DONE:
            taskList.completeTask(inputStringArr[1]);
            break;
        case DELETE:
            taskList.deleteTask(inputStringArr[1]);
            break;
        case TODO:
            taskList.addTodo(inputString);
            break;
        case DEADLINE:
            taskList.addDeadline(inputString);
            break;
        case EVENT:
            taskList.addEvent(inputString);
            break;
        case BYE:
            isScanning = false;
            break;
        default:
            throw new NoSuchMethodException();
        }
        return isScanning;
    }

    /**
     * Takes a string and returns a enum Command
     * Treats an unknown command as regular task
     */
    private static Command getCommandType(String commandString) throws NoSuchMethodException{
        Command command;
        String[] inputStringArr = commandString.split(" ");

        switch (inputStringArr[0]) {
        case "list":
            command = Command.LIST;
            break;
        case "done":
            command = Command.DONE;
            break;
        case "delete":
            command = Command.DELETE;
            break;
        case "todo":
            command = Command.TODO;
            break;
        case "deadline":
            command = Command.DEADLINE;
            break;
        case "event":
            command = Command.EVENT;
            break;
        case "bye":
            command = Command.BYE;
            break;
        default:
            throw new NoSuchMethodException();
        }
        return command;
    }
}
