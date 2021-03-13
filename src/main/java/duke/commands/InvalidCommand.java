package duke.commands;

import duke.exception.InvalidCommandException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;

/**
 * Represents the command given by the user if it does not match any of the default commands.
 */
public class InvalidCommand extends Command {

    public InvalidCommand(String commandArg) {
        super(commandArg);
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Parser parser) throws InvalidCommandException {
        throw new InvalidCommandException(commandArg);
    }
}