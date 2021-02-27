public class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return ("[T][" + getStatusIcon() + "] " + description);
    }

    public String getDescription() {
        return ("[" + getStatusIcon() + "] " + description);
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public void setIsDone(String isDone) {
        this.isDone = isDone.equals("1");
    }

    public String toSaveFormat() {
        return (description);
    }
}
