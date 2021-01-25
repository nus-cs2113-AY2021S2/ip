public class Task {
    private boolean isDone;
    private String taskName;

    public Task(String task){
        this.taskName = task;
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getStatusIcon(){
        return (isDone ? "[ \u2713 ] " : "[ \u2718 ] ");
    }
}
