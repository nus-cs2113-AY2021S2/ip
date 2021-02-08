package duke.task;

abstract public class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setAsDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    abstract public String getTaskType();

    @Override
    public String toString() {
        String mark = isDone ? "[X] " : "[ ] ";
        return mark + taskName;
    }
}
