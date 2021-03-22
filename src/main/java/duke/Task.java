package duke;
import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString(){
        return (isDone ? "[\u2718]" : "[ ]") + this.description;
    }

    /**
     * Mark a task done
     */
    public void markAsDone(){
        this.isDone = true;
    }
}
