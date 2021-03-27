package Class;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getType() {
        return "task";
    }

    public String getTime() {
        return "nothing";
    }

    public String getTaskName() {
        return this.description;
    }

    public int isDone() {
        if (this.isDone) {
            return 1;
        } else {
            return 0;
        }
    }
}
