package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ListCommand is called when user wants to view all their tasks
 */
public class ListCommand extends Command {
    private boolean isExit;

    public ListCommand() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Prints all tasks stored in TaskList tasks in an ordered list
     * @param tasks TaskList containing all tasks
     * @param ui User Interface
     * @param storage Storage to save all tasks, updating the data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasksList(tasks);
    }
}
