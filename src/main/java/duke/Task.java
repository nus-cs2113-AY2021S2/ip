package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType;
    private static char NOT_DONE_SYMBOL = '\u2718';
    private static char DONE_SYMBOL = '\u2713';

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription() {return description;}

    /**
     * Returns the complete task description in task list
     * @return the task status and description in the task list
     */
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the task status icon, either ✓(Done=True) or X(Not Done=False)
     * @return the respective status icon
     */
    public char getStatusIcon() {
        return (isDone ? DONE_SYMBOL : NOT_DONE_SYMBOL); //return Yes or No symbols
    }
    public void setTaskType(char taskType){
        this.taskType = taskType;
    }
    public char getTaskType(){
        return taskType;
    }

    /**
     * Set the task status
     * @param Done set the done status of a task to be true/false
     */
    public void setTaskStatus(boolean Done){
        this.isDone = Done;
    }
    public boolean getTaskStatus(){
        return isDone;
    }
    public String getFileStatus(){return (isDone ? "1" : "0"); }

}
