package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        boolean isExit = false;
        if (this.getClass().equals(ExitCommand.class)) {
            isExit = true;
        }
        return isExit;
    }
}
