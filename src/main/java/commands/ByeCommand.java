package commands;

/**Bye command class contains the command keyword for the bye command and methods to trigger the flag to end the command input loop*/
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_EXIT_ACKNOWEDGEMENT = "Bye. Hope to see you again soon!";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

    /**isBye checks if the input command is a bye command
     * @param command the input command
     */
    public static boolean isBye(Command command) {
        return command instanceof ByeCommand; // instanceof returns false if it is null
    }
}


