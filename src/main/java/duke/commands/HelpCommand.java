package duke.commands;

/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String MESSAGE_USAGE = "Invalid command. Here are all the possible commands:";

    public HelpCommand() {
        execute();
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_USAGE
                + "\n" + AddTodoCommand.MESSAGE_USAGE
                + "\n" + AddDeadlineCommand.MESSAGE_USAGE
                + "\n" + DeleteCommand.MESSAGE_USAGE
                + "\n" + ListCommand.MESSAGE_USAGE
                + "\n" + DoneCommand.MESSAGE_USAGE
                + "\n" + ExitCommand.MESSAGE_USAGE
        );
    }
}
