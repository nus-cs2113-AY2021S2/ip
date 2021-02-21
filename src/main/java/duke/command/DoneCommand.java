package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Implements done command objects.
 *
 * @author Leonardo Irvin Pratama
 */
public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the given command.
     *
     * @param tasks   Task list the user currently have.
     * @param ui      Tool to interact with user.
     * @param storage Storage to load and save data.
     * @return Responses to be passed to user.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markAsDone(index, ui);
        storage.saveTasks(tasks.getTasks());
        ui.getResponses();
    }
}
