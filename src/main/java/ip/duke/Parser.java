package ip.duke;


import ip.duke.exception.*;

public class Parser {

    private static final int ONE_SPACE_LENGTH = 1;
    private static final int START_POSITION = 0;

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
            commandType = input.substring(START_POSITION, lengthOfCommandType);
        }
        String commandContent = input.substring(lengthOfCommandType + ONE_SPACE_LENGTH);
        switch (commandType) {
        case "help":
            parseHelpCommand();
            break;
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
        case "date":
            parseDateCommand(commandContent);
            break;
        default:
            break;
        }
    }

    private static void parseHelpCommand() {
        Ui.printHelpMessage();
    }

    private static void parseTodoCommand(String description) {
        TaskList.updateTodo(description);
    }

    private static void parseDeadlineCommand(String commandContent) {
        try {
            TaskList.updateDeadline(commandContent);
        } catch (TimeException e) {
            Ui.printDeadlineMissingMessage();
        } catch (DateException e) {
            Ui.printDateInvalidMessage();
        }
    }

    private static void parseEventCommand(String commandContent) {
        try {
            TaskList.updateEvent(commandContent);
        } catch (TimeException e) {
            Ui.printEventTimeMissingMessage();
        } catch (DateException e) {
            Ui.printDateInvalidMessage();
        }
    }

    private static void parseListCommand() {
        TaskList.getCompleteList();
    }

    private static void parseDoneCommand(String commandContent) {
        int doneIndex = Integer.parseInt(commandContent);
        try {
            TaskList.markDone(doneIndex);
        } catch (IndexException e) {
            Ui.printDoneInvalidMessage();
        } catch (ListException e) {
            Ui.printEmptyListMessage();
        }
    }

    private static void parseDeleteCommand(String commandContent) {
        int deletedIndex = Integer.parseInt(commandContent);
        try {
            TaskList.deleteTask(deletedIndex);
        } catch (IndexException e) {
            Ui.printDeletedInvalidMessage();
        } catch (ListException e) {
            Ui.printEmptyListMessage();
        }
    }

    private static void parseFindCommand(String commandContent) {
        try {
            TaskList.getFoundTask(commandContent);
        } catch (ListException e) {
            Ui.printEmptyListMessage();
        }
    }

    private static void parseDateCommand(String commandContent) {
        try {
            TaskList.getDateTask(commandContent);
        } catch (DateException e) {
            Ui.printDateInvalidMessage();
        } catch (ListException e) {
            Ui.printEmptyListMessage();
        }
    }

}