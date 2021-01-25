public class Task {
    protected String description;
    protected boolean isDone;
    //can create static variable to get number of tasks (the range for indexes for the "done" command in main)

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setAsDone(){
        this.isDone = true;
    }
}
