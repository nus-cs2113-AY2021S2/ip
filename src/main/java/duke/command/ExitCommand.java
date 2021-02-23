package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Handles exit command.
 */
public class ExitCommand extends Command {
    /**
     * Prints exit message.
     *
     * @param taskList Task list consisting of all tasks.
     * @param ui User interface for printing result.
     * @param storage Storage for storing task list data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
    }

    /**
     * {@inheritDoc}
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
