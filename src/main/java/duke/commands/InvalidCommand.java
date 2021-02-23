package duke.commands;

import duke.exception.InvalidCommandException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;

public class InvalidCommand extends Command {

    public InvalidCommand(String commandArg) {
        super(commandArg);
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Parser parser) throws InvalidCommandException {
        throw new InvalidCommandException(commandArg);
    }
}