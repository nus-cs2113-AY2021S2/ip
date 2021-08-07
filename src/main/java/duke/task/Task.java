package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTaskType() {
        return null;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public String getTaskItem() {
        return this.description;
    }

    public String getFileFormat() {
        return this.description;
    }
}
