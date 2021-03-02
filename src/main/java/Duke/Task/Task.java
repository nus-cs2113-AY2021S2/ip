package Duke.Task;

import static Duke.UI.TEXT.*;

public class Task {
    protected String taskDescription;
    protected boolean isDone;


    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? DONE_ICON : SPACE);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() { return isDone; }

    public String getTask() {
        return taskDescription;
    }

    public String getTaskType() {
        return null;
    }

    public String getTaskTime() {
        return null;
    }

    public String toString() {
        return LEFT_SQUARE_BRACKET + getStatusIcon() + RIGHT_SQUARE_BRACKET + getTask();
    }
}
