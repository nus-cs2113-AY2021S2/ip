package Duke.Command;

import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

/**
 * ExitCommand helps the user exit the program.
 */
public class ExitCommand extends Command {

    @Override
    public boolean shouldExit() {
        return true;
    }

    /**
     * Changes isExit to be true, prints the exit message and exit the program.
     *
     * @param tasks the list of tasks
     * @param ui outputs to be printed
     * @param storage store data into Duke.txt
     * @throws DukeException the exceptions which can happen
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, TaskList matchedTasks, TaskList sameDateTasks)
            throws DukeException {
        // Do nothing
    }
}
