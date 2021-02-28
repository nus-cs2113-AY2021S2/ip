package duke.commands;

import duke.exception.DukeException;
import duke.file.Storage;
import duke.task.TaskList;

/*
Abstract class Command to be inherited and implemented
*/
public abstract class Command {
    protected String activity;
    protected String action;
    protected Boolean isExit;

    /*
    Constructor for Command
    Initialize activity, action and isExit variables
     */
    public Command(String activity, String action){
        this.activity = activity;
        this.action = action;
        isExit = false;
    }

    /*
    Abstract method to be implemented by subclasses
     */
    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;

    /*
    Set isExit status to true when command is "bye"
     */
    public boolean isExit(){
        if(activity.equals("bye")){
            isExit = true;
        }else{
            isExit = false;
        }
        return isExit;
    }

    /*
    Function to set isExit to true or false based on argument value
     */
    public void setExitCondition(Boolean status){
        isExit = status;
    }
}
