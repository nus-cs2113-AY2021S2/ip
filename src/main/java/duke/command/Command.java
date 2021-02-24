package duke.command;

import duke.input.InputData;
import duke.input.InputType;
import duke.record.Record;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.Scanner;

public class Command {
    private final Record record;
    private final Scanner scan = new Scanner(System.in);

    public Command(Record record){
        this.record = record;
    }

    public boolean receiveCommand() throws DukeException {
        boolean isLoop = true;
        InputData userInput = getUserInput();
        switch (userInput.getFirstArgument()) {
        case "todo":
            record.addRecord(userInput.getOtherArguments(), Todo.TASK_TYPE);
            break;
        case "deadline":
            record.addRecord(userInput.getOtherArguments(), Deadline.TASK_TYPE);
            break;
        case "event":
            record.addRecord(userInput.getOtherArguments(), Event.TASK_TYPE);
            break;
        case "list":
            showList(userInput.getOtherArguments());
            break;
        case "done":
            processCommand(userInput.getOtherArguments(), CommandType.done);
            break;
        case "delete":
            processCommand(userInput.getOtherArguments(), CommandType.delete);
            break;
        case "find":
            findDate(userInput.getOtherArguments());
            break;
        case "bye":
            isLoop = isEndProgram(userInput.getOtherArguments());
            break;
        default:
            throw new DukeException();
        }
        return isLoop;
    }

    private void findDate(String[] arguments) {
        if (arguments.length != 1) {
            System.out.printf("Command \"%s\" requires a date argument. Please try again!\n", "find");
            return;
        }
        record.findDate(arguments[0]);
    }

    private void processCommand(String[] arguments, CommandType commandType) {
        if (arguments.length != 1) {
            System.out.printf("Command \"%s\" requires an integer argument. Please try again!\n", commandType);
            return;
        }
        int targetRecordIndex = -1;
        boolean isArgumentInteger = true;
        try {
            targetRecordIndex = Integer.parseInt(arguments[0]) - 1;
        } catch (NumberFormatException e) {
            isArgumentInteger = false;
        }

        if (isArgumentInteger) {
            switch (commandType) {
            case delete:
                record.deleteRecord(targetRecordIndex);
                break;
            case done:
                record.markAsDone(targetRecordIndex);
                break;
            default:
                throw new IllegalArgumentException("Invalid commandType! Program terminated.");
            }
        } else {
            System.out.printf("Command \"%s\" only requires an integer argument. Please try again!\n", commandType);
        }
    }

    private void showList(String[] arguments) {
        if (arguments.length != 0) {
            System.out.println("Command \"list\" requires no argument. Please try again!");
            return;
        }
        record.showList();
    }

    private InputData getUserInput() {
        String userInput = "dummy";
        if (scan.hasNextLine()) {
            userInput = scan.nextLine();
        }
        System.out.println("Command entered: " + userInput);
        return new InputData(userInput, InputType.userInput);
    }

    private boolean isEndProgram(String[] arguments) {
        if (arguments.length != 0) {
            System.out.println("Command \"bye\" requires no argument. Please try again!");
            return true;
        }

        quitProgram();
        return false;
    }

    private void quitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
