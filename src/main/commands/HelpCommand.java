package main.commands;

/**
 * Shows commands that Duke accepts.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    @Override
    public CommandResult execute() {
        return new CommandResult(
                "\tAccepted Commands:"
                        + "\n\t\t" + TodoAddCommand.COMMAND_WORD
                        + "\n\t\t" + DeadlineAddCommand.COMMAND_WORD
                        + "\n\t\t" + EventAddCommand.COMMAND_WORD
                        + "\n\t\t" + MarkDoneCommand.COMMAND_WORD
                        + "\n\t\t" + DeleteCommand.COMMAND_WORD
                        + "\n\t\t" + ListCommand.COMMAND_WORD
                        + "\n\t\t" + SaveCommand.COMMAND_WORD
                        + "\n\t\t" + LoadCommand.COMMAND_WORD
                        + "\n\t\t" + HelpCommand.COMMAND_WORD
                        + "\n\t\t" + ExitCommand.COMMAND_WORD,
                taskList
        );
    }
}
