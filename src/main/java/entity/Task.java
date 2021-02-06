package entity;

/**
 * An entity representing a Task.
 */
public class Task {

    private String taskDescription;
    private boolean isDone;

    public Task() {
    }

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Create a Task from its description and whether it is done
     * @param taskDescription
     * @param isDone
     */
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

    @Override
    public String toString() {
        String checkMark = isDone ? "X" : " ";
        return String.format("[%s] %s", checkMark, taskDescription);
    }
}
