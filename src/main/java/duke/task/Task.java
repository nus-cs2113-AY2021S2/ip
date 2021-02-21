package duke.task;

public abstract class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    public abstract String getData();

    public String getDescription(){
        return taskName;
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
