public class Task {
    protected String taskDescription;
    protected boolean isDone;
    protected String taskType;

    public Task(String inputTask) {
        this.taskDescription = inputTask;
        this.isDone = false;
        this.taskType = "[ ]";

    }

    public String getStatusIcon() {
        //if task is done, mark with X
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return taskType + this.getStatusIcon() + this.taskDescription;
    }

}