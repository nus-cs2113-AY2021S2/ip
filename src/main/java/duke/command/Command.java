package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {
    protected boolean exit;

    public Command() {
        exit = false;
    }

    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws DukeExceptions;

    public abstract boolean isExit();
}