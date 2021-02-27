package duke.commands;

import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

import static duke.common.Messages.HELP_USAGE_MESSAGE;

public class HelpCommand extends Command {
    public static final String HELP_WORD = "help";

    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        ui.printStatements(HELP_USAGE_MESSAGE);
    }
}
