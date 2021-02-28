package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Implements bye command objects.
 *
 * @author Leonardo Irvin Pratama
 */
public class GoodbyeCommand extends Command {
    /**
     * Executes the given command.
     *
     * @param tasks   Task list the user currently have.
     * @param ui      Tool to interact with user.
     * @param storage Storage to load and save data.
     * @throws DukeException If failed to save tasks.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(tasks.getTasks());
        ui.showGoodbyeUser();
    }

    /**
     * Returns an indicator if the program will terminate.
     *
     * @return Indicator of termination.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
