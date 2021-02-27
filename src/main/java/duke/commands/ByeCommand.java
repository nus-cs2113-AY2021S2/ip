package duke.commands;

import duke.data.exceptions.InvalidCommandException;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

public class ByeCommand extends Command {
    public static final String BYE_WORD = "bye";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public ByeCommand(String commandArgs) throws InvalidCommandException {
        if (commandArgs.length() > 0) {
            // This command is not supposed to have arguments.
            throw new InvalidCommandException();
        }
    }

    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        isExit = true;
        ui.printStatements(EXIT_MESSAGE);
    }
}
