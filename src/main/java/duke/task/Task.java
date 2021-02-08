package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone() {
        isDone = true;
        System.out.print("Nice! I've marked this task as done:\n" + this.toString() + '\n');
    }

    public String getStatusIcon() {
        if(isDone) {
            return "[\u2713] ";
        }
        return "[\u2718] ";
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }


}
