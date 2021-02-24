package duke.command;

import duke.storage.Storage;
import duke.task.*;

public class DisplayCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeExceptions {
        if (taskList.isEmpty()) {
            throw new DukeExceptions();
        }
        ui.showDisplayTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return exit;
    }
}
