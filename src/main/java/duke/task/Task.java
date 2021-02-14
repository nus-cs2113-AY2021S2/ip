package duke.task;

public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    public void setAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        char doneChar;
        if (isDone) {
            doneChar = 'X';
        } else {
            doneChar = ' ';
        }
        return String.format("[%c] %s", doneChar, taskName);
    }
}
