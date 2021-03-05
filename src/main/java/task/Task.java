package task;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns status icon
     * @return tick or X symbol
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Prints task
     */
    public void printTask() {
        System.out.println("[ ][" + getStatusIcon() + "] " + description);
    }
    /**
     * Saves task
     * @return empty string
     */
    public String saveTask() {
        return "";
    }
}