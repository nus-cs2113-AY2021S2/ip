package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Handles list command.
 */
public class ListCommand extends Command {
    /**
     * Prints all task in the task list.
     *
     * @param taskList Task list consisting of all tasks.
     * @param ui User interface for printing result.
     * @param storage Storage for storing task list data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTasks(taskList);
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
