public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    // Returns status icon
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick or X symbols
    }
    // Marks task as done
    public void markAsDone() {
        this.isDone = true;
    }
    // Prints task
    public void printTask() {
        System.out.println("[ ][" + getStatusIcon() + "] " + description);
    }
}