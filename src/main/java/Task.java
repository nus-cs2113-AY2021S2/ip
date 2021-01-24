public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone ? "X" : " "); //in the example, X means completed and space means incomplete
    }

    public void setStatus(boolean isDone){
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }
}
