public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String task) {
        this.taskDescription = task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getTaskDescription(){
        return taskDescription;
    }
}
