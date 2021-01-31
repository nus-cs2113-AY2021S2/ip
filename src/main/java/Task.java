public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public void setTaskType(char taskType){
        this.taskType = taskType;
    }
    public char getTaskType(){
        return taskType;
    }
    public void setTaskStatus(boolean Done){
        this.isDone = Done;
    }
    public boolean getTaskStatus(){
        return isDone;
    }

}
