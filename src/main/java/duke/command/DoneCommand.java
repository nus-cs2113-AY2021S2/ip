package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

/**
 * Handles done command.
 */
public class DoneCommand extends Command {
    /**
     * Index number of task to be marked as done.
     */
    private int index;

    /**
     * Constructs a new done command.
     *
     * @param index Index number of task to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    /**
     * Retrieves task from task list based on index number and mark the task as done.
     * Prints task marked as done message after marking the task as done.
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
            taskList.markAsDone(task);
            storage.storeTasksToFile(taskList);
            ui.showMarkAsDoneMessage(task);
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
