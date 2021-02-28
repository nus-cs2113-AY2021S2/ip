package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;
import duke.task.Deadline;

/**
 * Handles deadline command.
 */
public class DeadlineCommand extends Command {
    /**
     * Description for deadline task.
     */
    private String description;
    /**
     * Deadline for deadline task.
     */
    private String by;

    /**
     * Constructs a new deadline command.
     *
     * @param description Description for deadline task.
     * @param by Deadline for deadline task.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    public String getDescription() {
        return description;
    }

    public String getBy() {
        return by;
    }

    /**
     * Creates and Adds new deadline task.
     * Shows task added message after adding the task.
     *
     * @param taskList Task list consisting of all tasks.
     * @param ui User interface for printing result.
     * @param storage Storage for storing task list data.
     * @throws DukeException If it fails to store task list data to disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = new Deadline(getDescription(), getBy());
        taskList.add(task);
        storage.storeTasksToFile(taskList);
        ui.showAddTaskMessage(task, taskList);
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
