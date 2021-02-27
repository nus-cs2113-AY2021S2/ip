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
