package command;

import task.TaskList;

public abstract class Command {
    protected TaskList tasks;
    protected static String PRE_SPACE = "    ";

    public abstract CommandResult execute();


    public void setData(TaskList tasks){
        this.tasks = tasks;
    }

}
