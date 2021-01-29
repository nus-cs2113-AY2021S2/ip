public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType;

    public Task(String description, char taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setTaskStatus(boolean isDone){
        this.isDone = isDone;
    }

    public boolean getTaskStatus(){
        return isDone;
    }

    public void setTaskType(char taskType){
        this.taskType = taskType;
    }

    public char getTaskType(){
        return taskType;
    }

}
