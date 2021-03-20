package duke.Commands;

import duke.Storage;
import duke.TaskList;
import duke.Tasks.Task;
import duke.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {

    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes a <code>Task</code> as specified by the user from the <code>taskList</code>
     * @param taskList The list of tasks stored in Duke
     * @param ui The <code>Ui</code> object that shows the output to the user
     * @param storage The <code>Storage</code> object that saves the updated tasks to the file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task currentTask;
        try {
            currentTask = taskList.getTaskAtIndex(taskIndex);
            taskList.deleteTaskAtIndex(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            ui.showNotInRange(taskIndex, taskList.getSize());
            return;
        }

        ui.showDeleteTask(currentTask, taskList.getSize());

        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.showSaveError();
        }
    }
}
