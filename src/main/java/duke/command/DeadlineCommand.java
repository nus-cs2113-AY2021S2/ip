package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Implements deadline command objects.
 *
 * @author Leonardo Irvin Pratama
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String due;

    /**
     * Initializes a DeadlineCommand object.
     *
     * @param description The task description.
     * @param due         The task deadline time.
     */
    public DeadlineCommand(String description, String due) {
        this.description = description;
        this.due = due;
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
        Task newTask = new Deadline(description, false, due);
        tasks.addTask(newTask, ui);
        storage.saveTasks(tasks.getTasks());
        ui.getResponses();
    }
}
