/**
 * Task to be added
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Task constructor
     * @param description name of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * output of tasks
     * @return response of task
     */
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * the status of task
     * @return the status of task as done or undone
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * mark the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * write the task to file
     * @return writing task name to file
     */
    public String write() {
        return this.description;
    }
}