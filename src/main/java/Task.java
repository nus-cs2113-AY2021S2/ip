public class Task {
    private String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    public void setAsDone(){
        isDone = true;
    }
    public String getTask(){
        return description;
    }
    public String getStatusIcon(){
        return (isDone ? "\u2718":" ");
    }

}
