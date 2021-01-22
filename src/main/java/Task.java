public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDescription(boolean isDone){
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

}
