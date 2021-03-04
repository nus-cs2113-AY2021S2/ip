package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * All commands are subclasses of this abstract Command class.
 * This forces all commands to have execute(tasks,ui,storage) and isExit() methods.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
