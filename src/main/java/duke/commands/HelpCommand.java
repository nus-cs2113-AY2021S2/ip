package duke.commands;

import duke.data.exceptions.InvalidCommandException;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

import static duke.common.Messages.HELP_USAGE_MESSAGE;

public class HelpCommand extends Command {
    public static final String HELP_WORD = "help";

    public HelpCommand(String commandArgs) throws InvalidCommandException {
        if (commandArgs.length() > 0) {
            // This command is not supposed to have arguments.
            throw new InvalidCommandException();
        }
    }

    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        ui.printStatements(HELP_USAGE_MESSAGE);
    }
}
