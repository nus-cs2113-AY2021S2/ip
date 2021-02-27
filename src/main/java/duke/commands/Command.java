package duke.commands;

import duke.TaskList;
import duke.Ui;

public abstract class Command {
    protected TaskList tasks = new TaskList();
    protected Ui ui = new Ui();
    protected boolean shouldExit = false;

    public abstract void execute();

    public boolean isExit() {
        return this.shouldExit;
    }
}
