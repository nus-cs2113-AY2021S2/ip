public class Task {
    protected String description;
    protected boolean isDone;

    public static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone(){
        if (!this.isDone) {
            this.isDone = true;
            System.out.println("Great! I've marked this task as done: ");
            System.out.println(this.toString());
        } else {
            System.out.println("You already completed this task previously!");
        }
    }

    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
