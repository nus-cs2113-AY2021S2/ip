package commands;

public class byeCommand extends Command{

    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_EXIT_ACKNOWEDGEMENT = "Bye. Hope to see you again soon!";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

    public static boolean isBye(Command command) {
        return command instanceof byeCommand; // instanceof returns false if it is null
    }
}


