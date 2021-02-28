package duke.task;

/*
Class Task for creating task objects
*/
public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType;

    /*
    Constructor Task Object
    Initialize description, isDone, taskType variables
    */
    public Task(String description, char taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /*
    Returns status icon of tick or X symbols
    */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /*
    Overrides toString() method from superclass
    Returns a string of task details
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

    /*
    Returns description
    */
    public String getDescription(){
        return description;
    }

    /*
    Function that sets the isDone variable according to argument passed in
    */
    public void setTaskStatus(boolean isDone){
        this.isDone = isDone;
    }

    /*
    Returns isDone status
    */
    public boolean getTaskStatus(){
        return isDone;
    }

    /*
    Returns task type
    */
    public char getTaskType(){
        return taskType;
    }

}
