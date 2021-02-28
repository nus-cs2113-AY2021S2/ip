package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;
import duke.task.Todo;

/**
 * Handles todo command.
 */
public class TodoCommand extends Command {
    /**
     * Description for the todo task.
     */
    private String description;

    /**
     * Constructs a new todo command.
     *
     * @param description Description for the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Creates and Adds new todo task.
     * Shows task added message after adding the task.
     *
     * @param taskList Task list consisting of all tasks.
     * @param ui User interface for printing result.
     * @param storage Storage for storing task list data.
     * @throws DukeException If it fails to store task list data to disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = new Todo(getDescription());
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
