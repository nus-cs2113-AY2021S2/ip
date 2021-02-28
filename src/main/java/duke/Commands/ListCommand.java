package duke.Commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    /**
     * Lists the <code>Task</code> objects in the <code>taskList</code>
     * @param taskList The list of tasks stored in Duke
     * @param ui The <code>Ui</code> object that shows the output to the user
     * @param storage The <code>Storage</code> object that saves the updated tasks to the file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getSize() == 0) {
            ui.showNoTasks();
            return;
        }
        ui.showTasks(taskList);
    }
}
