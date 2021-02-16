package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.UnknownCommandException;
import duke.task.Task;

import java.util.ArrayList;

public class Parser {

    /**
     * Takes in a user input and returns a duke.command.Command object.
     * @param userInput
     * @return Command object based on command type given.
     */
    public static Command parseUserInput(String userInput) throws UnknownCommandException {
        String[] commandAndArgs = getCommandAndArgs(userInput);
        String commandType = commandAndArgs[0];
        String commandArgs = commandAndArgs[1];

        switch (commandType) {
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(commandArgs);
        case "bye":
            return new ByeCommand();
        case "todo":
            return new TodoCommand(commandArgs);
        case "deadline":
            return new DeadlineCommand(commandArgs);
        case "event":
            return new EventCommand(commandArgs);
        case "delete":
            return new DeleteCommand(commandArgs);
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Takes in user input and splits it into 2 as long as there is whitespace
     * character in the middle.
     * Reused from Lecture Week 4 Contacts program.
     * @param userInput
     * @return Array of command type and command arguments
     */
    private static String[] getCommandAndArgs(String userInput) {
        String[] split = userInput.split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0], "" };
    }

    /**
     * Checks if input task number is valid.
     * @param tasks, number
     * @return validity of input.
     */
    public static boolean isValidTaskNumber(ArrayList<Task> tasks, String number) {
        try {
            tasks.get(Integer.parseInt(number) - 1);
        } catch (NumberFormatException e){
            return false;
        } catch (IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }
}
