import java.util.Scanner;

public class Bob {
    public static final String LINE_STRING = "____________________________________________________________\n";
    public static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        welcomeMessage();
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
        String inputString = "";
        Command commandType = null;

        while (isScanning) {
            try {
                inputString = in.nextLine();
                commandType = getCommandType(inputString);
                isScanning = scanSwitch(inputString, commandType);
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

    private static void printInputMismatch() {
        String exceptionMessage = LINE_STRING +
                " 😥 There is some issue with getting you input\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    private static void printNoSuchMethod() {
        String exceptionMessage = LINE_STRING +
                " 😥 I don't quite get what you mean dude\n" +
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
        default:
            throw new NoSuchMethodException();
        }
        return isScanning;
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

    private static void addTodo(String command) throws NoCommandLabelException {
        String commandType = Command.TODO.name().toLowerCase();
        String label = getLabel(command,commandType);

        taskList.addToList(new Todo(label));
    }

    /**
     * Phrases command into label and startTime parts
     * Followed by creating event object
     */
    private static void addEvent(String command) throws NoCommandLabelException, NoCommandFormatException {
        final String TIME_MARKER = "/at";
        String commandType = Command.EVENT.name().toLowerCase();
        String[] commandArray = command.split(TIME_MARKER);

        if (commandArray.length < 2) {
            throw new NoCommandFormatException();
        }

        String label = getLabel(commandArray[0],commandType);
        String startTime = commandArray[1].trim();

        taskList.addToList(new Event(label, startTime));
    }

    /**
     * Phrases command into label and dueTime parts
     * Followed by creating Deadline object
     */
    private static void addDeadline(String command) throws NoCommandLabelException, NoCommandFormatException {
        final String TIME_MARKER = "/by";
        String commandType = Command.DEADLINE.name().toLowerCase();
        String[] commandArray = command.split(TIME_MARKER);

        if (commandArray.length < 2) {
            throw new NoCommandFormatException();
        }

        String label = getLabel(commandArray[0],commandType);
        String dueTime = commandArray[1].trim();

        taskList.addToList(new Deadline(label, dueTime));
    }

    private static String getLabel(String string, String commandType) throws NoCommandLabelException {
        String label = string.replaceFirst(commandType,"").trim();
        if (label.isEmpty()) {
            throw new NoCommandLabelException();
        }
        return label;
    }
}
