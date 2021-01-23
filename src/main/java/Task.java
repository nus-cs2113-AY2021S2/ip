public class Task {
    protected String description;
    protected boolean isDone;

    private static int totalTasks=0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalTasks ++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription(){
        return this.description;
    }

    public void markAsDone(){
        this.isDone = true;
    }


}