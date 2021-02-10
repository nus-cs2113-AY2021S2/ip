package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString(){
        return (isDone ? "[\u2718]" : "[ ]") + this.description;
    }

    public void markAsDone(){
        this.isDone = true;
    }
}
