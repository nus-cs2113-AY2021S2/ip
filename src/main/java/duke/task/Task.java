package duke.task;

/**
 * Task class - object corresponds to a task represented by name and check mark.
 */
public abstract class Task {
    private final String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markAsDone(){
        isDone = true;
    }

    public boolean checkIsDone(){
        return isDone;
    }

    public String getName() {
        return name;
    }

    public String getType(){
        return "";
    }

    public String getDate(){
        return "";
    }

    public String getStatusIcon() {
        //return tick as done and empty as not done
        return (isDone ? "\u2713" : " ");
    }

    abstract public String outputData();

}
