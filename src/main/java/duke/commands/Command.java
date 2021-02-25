package duke.commands;

import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

public abstract class Command {
    public abstract void execute(TaskList tasks, TextUI ui, Storage storage);

    private boolean isExit;

    public Command() {
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }
}
