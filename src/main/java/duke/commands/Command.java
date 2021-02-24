package duke.commands;

import duke.exception.EmptyCommandArgException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidCommandTimeException;
import duke.exception.InvalidTaskNumberException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;

/**
 * Represents a command given by the user.
 */
public abstract class Command {
    protected static Ui ui;
    protected String commandArg;
    protected boolean isExit;

    public Command() {
        ui = new Ui();
        isExit = false;
    }

    public Command(String commandArgString) {
        ui = new Ui();
        commandArg = commandArgString;
        isExit = false;
    }

    public abstract void execute(TaskList taskList, Ui ui, Parser parser) throws EmptyCommandArgException,
            InvalidCommandTimeException, InvalidTaskNumberException, InvalidCommandException;

    public boolean isEmptyArgument(String commandArg) {
        return commandArg.length() == 0;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }
}
