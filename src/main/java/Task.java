public class Task {
    protected String description;
    protected boolean isDone;

    private static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalTasks ++;
    }
    //"\u2713" = tick symbol, "\u2718" = cross symbol
    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return X if done or space if not
    }

    public String getDescription(){
        return this.description;
    }

    public void markAsDone(){
        this.isDone = true;
    }


}