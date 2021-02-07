import java.util.List;

public class Parser {

    /**
     * Takes in a user input and returns a Command object.
     * @param userInput
     * @return Command
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
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Takes in user input and splits it into 2 as long as there is whitespace
     * character in the middle, returns an array of String.
     * Reused from Lecture Week 4 Contacts program.
     * @param userInput
     * @return
     */
    private static String[] getCommandAndArgs(String userInput) {
        String[] split = userInput.split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0], "" };
    }
}
