package duke.commands;

import duke.TaskList;
import duke.Ui;

/**
 * Command is the abstract base class for all actions that can be done with
 * {@code TaskList} in this application.
 */
public abstract class Command {
    protected TaskList tasks = new TaskList();
    protected Ui ui = new Ui();
    protected boolean shouldExit = false;

    public abstract void execute();

    /**
     * Returns a flag indicating if the program should terminate.
     */
    public boolean isExit() {
        return this.shouldExit;
    }
}
