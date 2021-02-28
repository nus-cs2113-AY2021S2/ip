package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;
import duke.task.Event;

/**
 * Handles event command.
 */
public class EventCommand extends Command {
    /**
     * Description of the event task.
     */
    private String description;
    /**
     * Date/time of the event task.
     */
    private String at;

    /**
     * Constructs a new event command.
     *
     * @param description Description of the event task.
     * @param at Date/time of the event task.
     */
    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    public String getDescription() {
        return description;
    }

    public String getAt() {
        return at;
    }

    /**
     * Creates and Adds new event task.
     * Shows task added message after adding the task.
     *
     * @param taskList Task list consisting of all tasks.
     * @param ui User interface for printing result.
     * @param storage Storage for storing task list data.
     * @throws DukeException If it fails to store task list data to disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = new Event(getDescription(), getAt());
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
