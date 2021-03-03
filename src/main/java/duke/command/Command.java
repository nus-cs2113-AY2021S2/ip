package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {

    /**
     * Executes corresponding method based on type of command.
     *
     * @param tasks TaskList containing tasks
     * @param ui User Interface
     * @param storage Storage to load and save tasks
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns boolean isExit based on type of command.
     *
     * @return boolean isExit
     */
    public abstract boolean isExit();
}
