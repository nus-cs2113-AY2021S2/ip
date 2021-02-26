package duke.commands;

import duke.data.exceptions.InvalidCommandException;

public class CommandParser {
    /**
     * Parses the user input into its command word and argument (if exists),
     * and creates and returns a Command object with its specified arguments.
     * If the command word is invalid, throw InvalidCommandException.
     *
     * @param userInput input from the user.
     * @return a Command object to be executed by caller.
     * @throws InvalidCommandException If command word does not exist.
     * @see #parseCommand(String)
     */
    public static Command parse(String userInput) throws InvalidCommandException {
        String[] commandAndArgs = parseCommand(userInput);
        String commandName = commandAndArgs[0];
        String commandArgs = commandAndArgs[1];

        switch (commandName) {
        case ListCommand.LIST_WORD:
            return new ListCommand();
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
        case ByeCommand.BYE_WORD:
            return new ByeCommand();
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Splits the user input into a command word and command arguments string.
     * Returns an array containing the command word and arguments string.
     *
     * @return an array of size 2; first element is the command word
     *         and second element is the arguments string.
     */
    private static String[] parseCommand(String userInput) {
        final String[] commandAndArgs = userInput.trim().split("\\s+", 2);
        if (commandAndArgs.length == 1) {
            return new String[] {commandAndArgs[0], ""};
        }
        return commandAndArgs;
    }
}
