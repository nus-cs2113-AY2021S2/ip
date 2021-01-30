public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType;

    public Task(String description, char taskType) {
        this.description = description;
        this.taskType = taskType;
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
    public void setTaskStatus(boolean isDone){
        this.isDone = isDone;
    }
    public boolean getTaskStatus(){
        return isDone;
    }

}
