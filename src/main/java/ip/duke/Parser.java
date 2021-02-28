package ip.duke;

public class Parser {
    /**
     * Parses the user's input into a format that can be recognized by the Duke project program.
     * Apart from "help" "list" and "bye" commands, all other valid commands must be followed by further information.
     * If the input is not an acceptable command or cannot be recognized correctly by this program
     * it will throw a DukeException.
     *
     * @param input the string input by user, which is regarded as a command
     * @throws DukeException an exception that occurs when an invalid command is input
     */
    public static void parseCommand(String input) throws DukeException {
        int lengthOfCommandType = 0;
        String commandType;
        if (input.equals("help") || input.equals("list") || input.equals("bye")) {
            commandType = input;
        } else if (!input.contains(" ")) {
            throw new DukeException();
        } else {
            lengthOfCommandType = input.indexOf(' ');
            commandType = input.substring(0, lengthOfCommandType);
        }
        String commandContent = input.substring(lengthOfCommandType + 1);
        switch (commandType) {
        case "todo":
            parseTodoCommand(commandContent);
            break;
        case "deadline":
            parseDeadlineCommand(commandContent);
            break;
        case "event":
            parseEventCommand(commandContent);
            break;
        case "list":
            parseListCommand();
            break;
        case "done":
            parseDoneCommand(commandContent);
            break;
        case "delete":
            parseDeleteCommand(commandContent);
            break;
        case "find":
            parseFindCommand(commandContent);
            break;
        default:
            break;
        }
    }

    private static void parseTodoCommand(String description) {
        TaskList.updateTodo(description);
    }

    private static void parseDeadlineCommand(String commandContent) {
        int byTimePosition = commandContent.indexOf("/") + 4;
        String description = commandContent.substring(0, byTimePosition - 5);
        String byTime = commandContent.substring(byTimePosition);
        TaskList.updateDeadline(description, byTime);
    }

    private static void parseEventCommand(String commandContent) {
        int atTimePosition = commandContent.indexOf("/") + 4;
        String description = commandContent.substring(0, atTimePosition - 5);
        String atTime = commandContent.substring(atTimePosition);
        TaskList.updateEvent(description, atTime);
    }

    private static void parseListCommand() {
        TaskList.getCompleteList();
    }

    private static void parseDoneCommand(String commandContent) {
        int doneIndex = Integer.parseInt(commandContent);
        TaskList.markDone(doneIndex);
    }

    private static void parseDeleteCommand(String commandContent) {
        int deletedIndex = Integer.parseInt(commandContent);
        TaskList.deleteTask(deletedIndex);
    }

    private static void parseFindCommand(String commandContent) {
        TaskList.getFoundTask(commandContent);
    }
}