package entity;

public class Task {
    String taskDescription;
    boolean isDone;

    public Task() {
    }
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}