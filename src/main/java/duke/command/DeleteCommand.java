package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = taskList.get(getIndex());
            taskList.delete(task);
            storage.storeTasksToFile(taskList);
            ui.showDeleteTaskMessage(task, taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The index number is out of range :-(");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
