package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class that represents commands entered by user.
 */
public abstract class Command {
    protected CommandType commandType;
    protected String commandArgs;

    /**
     * Constructor for Command. For commands without arguments.
     * @param commandType the type of command
     */
    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    /**
     * Alternative constructor for Command. For commands with arguments.
     * @param commandType the type of command
     * @param commandArgs the command arguments
     */
    public Command(CommandType commandType, String commandArgs) {
        this.commandType = commandType;
        this.commandArgs = commandArgs;
    }

    /**
     * Abstract method that executes depending on its command type.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Abstract method that sets Duke for termination.
     * @return
     */
    public abstract boolean isExit();
}
