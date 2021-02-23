package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }
}
