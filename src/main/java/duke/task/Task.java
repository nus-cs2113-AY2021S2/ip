package duke.task;

public abstract class Task {
    private String description;
    private boolean isDone;
    private char taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = ' ';
    }

    public String getDescription() {
        return description;
    }

    public void setTaskType(char taskType) {
        this.taskType = taskType;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + taskType + "]" + "[" + getStatusIcon() + "] " + description;
    }

    private String getStatusIcon() {
        /* Return X if task is done, and space otherwise */
        return (isDone ? "X" : " ");
    }

    public String exportAsCSV() {
        return isDone + "," + taskType + "," + description;
    }
}
