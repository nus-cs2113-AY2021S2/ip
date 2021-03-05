public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task Class
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String toString() {
        return getStatusIcon() + description;
    }

    /**
     * Returns the current status icon of whether the task is done
     *
     * @return  [x] if task is done and [ ] if task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "[x] ":"[ ] "); //return tick or X symbols
    }
    public void markAsDone(){
        isDone = true;
    }

    public String getDescription() {
        return description;
    }
}
