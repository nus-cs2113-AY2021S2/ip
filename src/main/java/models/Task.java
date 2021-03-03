package models;

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
     * @return String with the description of the Task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark a Task as Done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * @return String with a tick symbol if the Task is done, else no tick
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " ");  // return tick symbol if done
    }

    /**
     * @return String of formatted data to be writted onto the text file
     */
    abstract public String formatData();

    /**
     * @return String containing the Task description
     */
    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + getDescription());
    }

}