package Classes;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * @return String with a cross sign if a Task is done, else no cross
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * @return String with description of Task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a Task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * @return String contains whether the Task is done and its description
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * @return String of formatted data to be written into the text file
     */
    public abstract String formatString();
}