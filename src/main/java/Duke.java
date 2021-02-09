import java.util.Locale;
import java.util.Scanner;

public class Duke {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final char INPUT_COMMENT_MARKER = '#';
    private static final int CAPACITY = 99;
    private static TaskManager tasks;


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
        System.out.println("Hello! I'm Duke, your truly daily helper");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

    }

    public static void showBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
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
        if(userInputString.equals("list")) {
            commandType = CommandType.LIST;
        } else if (userInputString.matches("^(done).*$")) {
            commandType = CommandType.DONE;
        } else if (userInputString.matches("^(todo).*$")) {
            commandType = CommandType.TODO;
        } else if (userInputString.matches("^(deadline).*$")) {
            commandType = CommandType.DEADLINE;
        } else if (userInputString.matches("^(event).*$")) {
            commandType = CommandType.EVENT;
        } else if (userInputString.equals("bye")) {
            commandType = CommandType.EXIT;
        } else {
            commandType = CommandType.UNDEFINED;
        }
        return commandType;
    }

    public static void getMessageForInvalidCommandInput() {
        System.out.println("Undefined!");
    }

    public static void executeAddTodo(String userCommand) throws EmptyDescriptionException{
        String[] typeContent = userCommand.split("todo",2);
        if (typeContent[1].equals("")) {
            throw new EmptyDescriptionException(CommandType.TODO);
        }

        tasks.addTodo(typeContent[1].trim());
    }

    public static void executeAddDeadline(String userCommand) throws EmptyDescriptionException{
        try {
            String[] typeContentBy = userCommand.trim().split("deadline", 2);
            String[] contentBy = typeContentBy[1].trim().split("/by", 2);
            if (contentBy[0].trim().equals("") || contentBy[1].trim().equals("")) {
                throw new EmptyDescriptionException(CommandType.DEADLINE);
            }
            tasks.addDeadline(contentBy[0].trim(), contentBy[1].trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No /by founded in the command");
        }

    }

    public static void executeAddEvent(String userCommand) throws EmptyDescriptionException{
        try {
            String[] typeContentAt= userCommand.trim().split("event", 2);
            String[] contentAt = typeContentAt[1].trim().split("/at", 2);
            if (contentAt[0].trim().equals("") || contentAt[1].trim().equals("")) {
                throw new EmptyDescriptionException(CommandType.EVENT);
            }
            tasks.addEvent(contentAt[0].trim(), contentAt[1].trim());
        } catch (IndexOutOfBoundsException e){
            System.out.println("No /at founded in the command");
        }

    }

    public static void executeList(String userCommand) {
        tasks.listAllTasks();
    }

    public static void executeDone(String userCommand) throws EmptyDescriptionException{
        int taskIndexShow = Integer.parseInt(userCommand.replaceAll("[^0-9]", ""));
        if(taskIndexShow <= 0 || taskIndexShow > tasks.getNumOfTasks()) {
            throw new EmptyDescriptionException(CommandType.DONE);
        }
        tasks.markTaskDone(taskIndexShow);
    }

    private static void executeExitProgramRequest() {
        showBye();
        System.exit(0);
    }

    public static void executeCommand(String userInputString) {
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
                getMessageForInvalidCommandInput();
                return;
            }
        } catch (EmptyDescriptionException e) {
            e.showMessage();
            return;
        }

    }

    public static void main(String[] args) {
        initTaskManager();
        showHello();
        while (true) {
            String userCommand = getUserInput();
            executeCommand(userCommand.trim());
        }
    }
}
