package Duke.Commands;

import Duke.Exceptions.RangeError;
import Duke.TaskList;
import Duke.Storage;

abstract public class Command {

    private boolean isExit = false;

    public abstract String execute(TaskList tasks, Storage storage) throws RangeError;

    public boolean isExit() {
        return isExit;
    }

    public void setExit() {
        this.isExit = true;
    }


}
