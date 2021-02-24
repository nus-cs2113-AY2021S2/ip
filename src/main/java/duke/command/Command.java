package duke.command;

import duke.TaskList;

public abstract class Command {
    protected TaskList tasks;
    protected boolean isExit;
    protected String commandWord;
    protected String description;

    public abstract CommandResult execute();

    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    public boolean isExit () {
        return isExit;
    }
}
