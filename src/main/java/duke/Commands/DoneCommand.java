package duke.Commands;

import duke.Storage;
import duke.TaskList;
import duke.Tasks.Task;
import duke.Ui;

import java.io.IOException;

public class DoneCommand extends Command {

    private final int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Updates a <code>Task</code> as specified by the user from the <code>taskList</code> to complete
     * @param taskList The list of tasks stored in Duke
     * @param ui The <code>Ui</code> object that shows the output to the user
     * @param storage The <code>Storage</code> object that saves the updated tasks to the file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task currentTask = taskList.getTaskAtIndex(taskIndex);
            if (currentTask.getDone()) {
                ui.showAlreadyCompleted(currentTask);
            } else {
                currentTask.setDone();
                ui.showMarkedDone(currentTask);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showNotInRange(taskIndex, taskList.getSize());
            return;
        }

        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.showSaveError();
        }
    }
}
