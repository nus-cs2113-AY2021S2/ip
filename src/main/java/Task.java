public class Task {
    protected String description;
    protected boolean isDone;
    private int taskNo;

    public Task(String Description) {
        this.description = Description;
        this.isDone = false;
    }

    public int getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(int taskNo) {
        this.taskNo = taskNo;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718] "); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String toString() {
        return getStatusIcon()  + description;
    }

}