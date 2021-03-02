package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone(){
        if (!this.isDone) {
            this.isDone = true;
            System.out.println("Great! I've marked this task as done:");
            System.out.println(this.toString());
        } else {
            System.out.println("You already completed this task previously!");
        }
    }

    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
