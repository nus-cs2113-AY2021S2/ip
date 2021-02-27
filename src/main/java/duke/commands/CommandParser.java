package duke.commands;

import duke.data.exceptions.InvalidCommandException;

public class CommandParser {
    private static final int COMMAND_WORD_INDEX = 0;
    private static final int COMMAND_ARGUMENT_INDEX = 1;
    private static final int PARSE_COMMAND_SIZE = 2;

    /**
     * Parses the user input into its command word and argument (if exists),
     * and creates and returns a Command object with its specified arguments.
     * If the command word is invalid, throw InvalidCommandException.
     *
     * @param userInput input from the user.
     * @return a Command object to be executed by caller.
     * @throws InvalidCommandException If command word does not exist.
     */
    public static Command parse(String userInput) throws InvalidCommandException {
        final String[] commandAndArgs = userInput.trim().split("\\s+", PARSE_COMMAND_SIZE);
        String commandName = commandAndArgs[COMMAND_WORD_INDEX];
        String commandArgs = (commandAndArgs.length == PARSE_COMMAND_SIZE)
                             ? commandAndArgs[COMMAND_ARGUMENT_INDEX]
                             : "";
        switch (commandName) {
        case ListCommand.LIST_WORD:
            return new ListCommand(commandArgs);
        case TodoCommand.TODO_WORD:
            return new TodoCommand(commandArgs);
        case DeadlineCommand.DEADLINE_WORD:
            return new DeadlineCommand(commandArgs);
        case EventCommand.EVENT_WORD:
            return new EventCommand(commandArgs);
        case DoneCommand.DONE_WORD:
            return new DoneCommand(commandArgs);
        case DeleteCommand.DELETE_WORD:
            return new DeleteCommand(commandArgs);
        case FindCommand.FIND_WORD:
            return new FindCommand(commandArgs);
        case DateCommand.DATE_WORD:
            return new DateCommand(commandArgs);
        case HelpCommand.HELP_WORD:
            return new HelpCommand(commandArgs);
        case ByeCommand.BYE_WORD:
            return new ByeCommand(commandArgs);
        default:
            throw new InvalidCommandException();
        }
    }
}
