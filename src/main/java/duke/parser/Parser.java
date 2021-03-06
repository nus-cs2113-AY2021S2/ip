package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DateCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.common.Utils;
import duke.exception.UnknownCommandException;

public class Parser {

    /**
     * Takes in user input string and returns a Command object.
     * @param userInput the user input string
     * @return Command object based on command type given.
     * @throws UnknownCommandException
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
        case "help":
            return new HelpCommand();
        default:
            throw new UnknownCommandException();
        }
    }
}
