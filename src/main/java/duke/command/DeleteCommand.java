package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

/**
 * Handles delete command.
 */
public class DeleteCommand extends Command {
    /**
     * Index number of task to be deleted.
     */
    private int index;

    /**
     * Constructs a new delete command.
     *
     * @param index Index number of task for deletion.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    /**
     * Retrieves task from task list based on index number and deletes the task.
     * Prints task deleted message after deleting the task.
     *
     * @param taskList Task list consisting of all tasks.
     * @param ui User interface for printing result.
     * @param storage Storage for storing task list data.
     * @throws DukeException If it fails to store task list data to list or index out of bound.
     */
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

    /**
     * {@inheritDoc}
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
