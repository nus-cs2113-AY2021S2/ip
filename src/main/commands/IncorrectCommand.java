package main.commands;


/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class IncorrectCommand extends Command {

    public static final String COMMAND_WORD = "incorrect";

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(feedbackToUser, taskList);
    }

}