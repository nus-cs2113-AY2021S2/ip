public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        System.out.print("Got it. I've added this task:\n" + this + '\n');
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
        System.out.print("Nice! I've marked this task as done:\n" + this + '\n');
    }

    public String getStatusIcon() {
        if(isDone) {
            return "[\u2713] ";
        }
        return "[\u2718] ";
    }

    public String toString() {
        return this.getStatusIcon() + this.description;
    }


}
