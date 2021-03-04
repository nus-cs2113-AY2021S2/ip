package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command {

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute(TaskList tasks, TextUi ui, Storage storage) {
        return new CommandResult(feedbackToUser);
    }

}
