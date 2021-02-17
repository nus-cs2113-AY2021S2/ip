package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDone() {
        isDone = true;
        System.out.print("Nice! I've marked this task as done:\n" + this.toString() + '\n');
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        if(isDone) {
            return "[\u2713] ";
        }
        return "[\u2718] ";
    }

    public String getTaskType() {
        return "Task";
    }

    public String getDate() {
        return "";
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }


}
