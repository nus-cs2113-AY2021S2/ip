package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command{

    /**
     * Prints farewell message and saves the text file before exiting.
     *
     * @param tasks TaskList containing tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bidGoodbye();
        storage.saveFile(tasks.getTasks());
    }

    /**
     * Returns true as command is of type bye.
     *
     * @return boolean true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
