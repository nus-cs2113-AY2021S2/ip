package duke.command;

import duke.exception.InvalidArgumentException;
import duke.input.InputParser;
import duke.input.InputType;
import duke.record.Record;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.Scanner;

/**
 * Represents a {@code CommandHandler} object. It receives and handles command provided by a user via CLI.
 */
public class CommandHandler {
    private final Record record;
    private final Scanner scan = new Scanner(System.in);

    /**
     * Constructor of CommandHandler<br>
     * Initializes the {@code CommandHandler} object by the given {@code Record} object.
     *
     * @param record A {@code Record} object that stores and manages user's tasks
     */
    public CommandHandler(Record record) {
        this.record = record;
    }

    /**
     * Receiving input string, processing it and executing the related command accordingly. It returns {@code ture}
     * normally so that the program continues to process inputs unless the command "bye" is inputted.
     * Also, If the command is invalid, exception {@code DukeException} will be thrown.
     *
     * @return boolean value of whether to continue processing user command
     * @throws DukeException if the command inputted is not valid
     */
    public boolean handleCommand() throws DukeException {
        boolean isLoop = true;
        InputParser userInput = getUserInput();
        switch (userInput.getCommand()) {
        case "todo":
            record.addRecord(userInput.getArguments(), Todo.TASK_TYPE);
            break;
        case "deadline":
            record.addRecord(userInput.getArguments(), Deadline.TASK_TYPE);
            break;
        case "event":
            record.addRecord(userInput.getArguments(), Event.TASK_TYPE);
            break;
        case "list":
            showList(userInput.getArguments());
            break;
        case "done":
            processCommand(userInput.getArguments(), CommandType.markAsDone);
            break;
        case "delete":
            processCommand(userInput.getArguments(), CommandType.delete);
            break;
        case "find":
            findRecords(userInput.getArguments());
            break;
        case "search":
            searchDate(userInput.getArguments());
            break;
        case "bye":
            isLoop = isContinueToRun(userInput.getArguments());
            break;
        default:
            throw new DukeException();
        }
        return isLoop;
    }

    private InputParser getUserInput() {
        String userInput = "dummy";
        if (scan.hasNextLine()) {
            userInput = scan.nextLine();
        }
        return new InputParser(userInput, InputType.userInput);
    }

    private void showList(String[] arguments) {
        if (arguments.length != 0) {
            printErrorMsg("Command \"list\" requires no argument. Please try again!");
            return;
        }
        record.showList();
    }

    private void processCommand(String[] arguments, CommandType commandType) {
        if (arguments.length != 1) {
            printErrorMsg("Command \"" + commandType + "\" requires an integer argument. Please try again!\n");
            return;
        }
        int targetRecordIndex = convertStringToIndex(arguments[0]);
        if (targetRecordIndex == -1) {
            printErrorMsg("Command \"" + commandType + "\" only requires an integer argument. Please try again!\n");
            return;
        }
        executeCommand(commandType, targetRecordIndex);
    }

    private int convertStringToIndex(String targetString) {
        int targetRecordIndex;
        try {
            targetRecordIndex = Integer.parseInt(targetString) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
        return targetRecordIndex;
    }

    private void executeCommand(CommandType commandType, int targetRecordIndex) {
        if (commandType.equals(CommandType.delete)) {
            record.deleteRecord(targetRecordIndex);
        } else if (commandType.equals(CommandType.markAsDone)) {
            record.markAsDone(targetRecordIndex);
        } else {
            throw new IllegalArgumentException("Unrecognized task type is provided.");
        }
    }

    private void findRecords(String[] arguments) {
        if (arguments.length != 1) {
            printErrorMsg("Command \"find\" requires 1 argument as keyword. Please try again!");
            return;
        }
        record.findRecords(arguments[0]);
    }

    private void searchDate(String[] arguments) {
        if (arguments.length != 1) {
            printErrorMsg("Command \"search\" requires a date argument. Please try again!\n");
            return;
        }
        try {
            record.searchDate(arguments[0]);
        } catch (InvalidArgumentException e) {
            printErrorMsg(e.getMessage());
        }

    }

    private boolean isContinueToRun(String[] arguments) {
        if (arguments.length != 0) {
            printErrorMsg("Command \"bye\" requires no argument. Please try again!");
            return true;
        }
        printQuitMsg();
        return false;
    }

    private void printQuitMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void printErrorMsg(String message) {
        System.out.println("Error:" + message);
    }
}
