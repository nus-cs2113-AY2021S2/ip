package duke.commands;

import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

public abstract class Command {
    protected boolean isExit;
    public abstract void execute(TaskList tasks, TextUI ui, Storage storage);

    public Command() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }
}
