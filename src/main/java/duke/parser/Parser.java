package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DateCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.common.Utils;
import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidDeadlineException;
import duke.exception.UnknownCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * Takes in a user input and returns a duke.command.Command object.
     * @param userInput
     * @return Command object based on command type given.
     */
    public static Command parseUserInput(String userInput) throws UnknownCommandException {
        String[] commandAndArgs = Utils.getCommandAndArgs(userInput);
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
        case "find":
            return new FindCommand(commandArgs);
        case "date":
            return new DateCommand(commandArgs);
        default:
            throw new UnknownCommandException();
        }
    }
}
