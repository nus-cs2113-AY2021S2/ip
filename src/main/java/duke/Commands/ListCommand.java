package duke.Commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getSize() == 0) {
            ui.showNoTasks();
            return;
        }
        ui.showTasks(taskList);
    }
}
