package duke.task;

/**
 * Represents a task, with a description and done status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        taskCount++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if a task is done, marks task as done if it is not.
     */
    public void markAsDone(){
        if (!this.isDone) {
            this.isDone = true;
            System.out.println("Great! I've marked this task as done:");
            System.out.println(this.toString());
        } else {
            System.out.println("You already completed this task previously:");
            System.out.println(this.toString());
        }
    }

    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
