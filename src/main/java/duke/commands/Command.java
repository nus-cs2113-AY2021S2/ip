package duke.commands;

import duke.exception.DukeException;
import duke.file.Storage;
import duke.task.TaskList;

public abstract class Command {
    protected String activity;
    protected String action;
    protected Boolean isExit;

    public Command(String activity, String action){
        this.activity = activity;
        this.action = action;
        isExit = false;
    }

    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;

    public boolean isExit(){
        if(activity.equals("bye")){
            isExit = true;
        }else{
            isExit = false;
        }
        return isExit;
    }

    public void setExitCondition(Boolean status){
        isExit = status;
    }
}
