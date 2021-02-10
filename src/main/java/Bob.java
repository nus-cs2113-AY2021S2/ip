import java.util.Scanner;

public class Bob {
    public static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        welcomeMessage();
        scanInput();
        goodbyeMessage();
    }

    private static void welcomeMessage() {
        String welcome = "____________________________________________________________\n" +
                " Hello! I'm Bob 😀\n" +
                " If you need anything hit me up fam 😌\n";
        System.out.println(welcome);
    }


    private static void scanInput() {
        Scanner in = new Scanner(System.in);
        scanLoop(in);
    }

    private static void goodbyeMessage() {
        String goodbye = "____________________________________________________________\n" +
                " Chao 👌\n" +
                "____________________________________________________________";
        System.out.println(goodbye);
    }

    /**
     * Scanner loop until bye command
     */
    private static void scanLoop(Scanner in) {
        boolean isScanning = true;
        while (isScanning) {
            String inputString = in.nextLine();
            isScanning = scanSwitch(inputString);
        }
    }

    /**
     * String before first space taken as command rest as args
     */
    private static boolean scanSwitch(String inputString) {
        String[] inputStringArr = inputString.split(" ");
        boolean isScanning = true;
        Command commandType = getCommandType(inputStringArr[0]);

        switch (commandType) {
        case LIST:
            printList();
            break;
        case DONE:
            completeTask(inputStringArr[1]);
            break;
        case TODO:
            addTodo(inputString);
            break;
        case DEADLINE:
            addDeadline(inputString);
            break;
        case EVENT:
            addEvent(inputString);
            break;
        case BYE:
            isScanning = false;
            break;
        default: //regular task
            addTask(inputString);
        }
        return isScanning;
    }

    private static void addTask(String inputString) {
        taskList.addToList(new Task(inputString));
    }

    private static void completeTask(String s) {
        taskList.completeTask(Integer.parseInt(s));
    }

    private static void printList() {
        taskList.printList();
    }

    /**
     * Takes a string and returns a enum Command
     * Treats an unknown command as regular task
     */
    private static Command getCommandType(String commandString) {
        Command command;
        switch (commandString) {
        case "list":
            command = Command.LIST;
            break;
        case "done":
            command = Command.DONE;
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
            command =  Command.TASK;
        }
        return command;
    }

    private static void addTodo(String command) {
        String label = command.substring("todo".length() + 1);
        taskList.addToList(new Todo(label));
    }

    /**
     * Phrases command into label and startTime parts
     * Followed by creating event object
     */
    private static void addEvent(String command) {
        final String timeMarker = "/at";
        String commandType = Command.EVENT.name().toLowerCase();

        String[] commandArray = command.split(timeMarker);
        String label = getLabel(commandArray[0],commandType);
        String startTime = commandArray[1].trim();

        taskList.addToList(new Event(label, startTime));
    }

    /**
     * Phrases command into label and dueTime parts
     * Followed by creating Deadline object
     */
    private static void addDeadline(String command) {
        final String timeMarker = "/by";
        String commandType = Command.DEADLINE.name().toLowerCase();

        String[] commandArray = command.split(timeMarker);
        String label = getLabel(commandArray[0],commandType);
        String dueTime = commandArray[1].trim();

        taskList.addToList(new Deadline(label, dueTime));
    }

    private static String getLabel(String string, String commandType) {
        return string.replaceFirst(commandType,"").trim();
    }
}
