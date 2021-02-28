package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Implements to do command objects.
 *
 * @author Leonardo Irvin Pratama
 */
public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the given command.
     *
     * @param tasks   Task list the user currently have.
     * @param ui      Tool to interact with user.
     * @param storage Storage to load and save data.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Todo(description, false);
        tasks.addTask(newTask, ui);
        storage.saveTasks(tasks.getTasks());
        ui.getResponses();
    }
}
