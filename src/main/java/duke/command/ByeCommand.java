package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(CommandType.BYE);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
