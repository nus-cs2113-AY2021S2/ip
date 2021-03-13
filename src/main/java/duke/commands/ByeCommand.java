package duke.commands;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;

/**
 * Represents the exit command given by the user.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        super();
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Parser parser) {
        setExit(true);
    }
}