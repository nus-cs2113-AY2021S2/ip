/**
 * information about a task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        System.out.println("Got it. I've added this task: ");
    }

    /**
     *
     * @return Y if it is done and N if it is not done
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N");
    }

    /**
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * mark a task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     *
     * @return a proper format
     */
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + description);
    }
}