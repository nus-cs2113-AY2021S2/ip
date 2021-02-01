public class Task {
    protected String description;
    protected boolean isDone;

    /** Total number of tasks keyed in Duke program **/
    private static int taskCount = 0;

    public Task(String description){
        this.description = description;
        this.isDone = false;
        taskCount += 1;
        
    }

    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");  //return tick or X symbols
    }

    public static int getTaskCount(){
        return taskCount;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void markAsDone(){
        isDone = true;
    }
}
