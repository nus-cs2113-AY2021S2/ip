package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    private static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalTasks++;
    }

    //"\u2713" = tick symbol, "\u2718" = cross symbol
    public String getStatusIcon() {
        //return X if done or space if not
        return (isDone ? "\u2718" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean getIsDone(){
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}