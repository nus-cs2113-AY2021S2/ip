package duke.myTasks;
import duke.myExceptions.NoContent;

public class Todo {
    public String name;
    protected boolean isDone;

    public Todo(String name) throws NoContent{
        if (name.equals("")) {
            throw new NoContent();
        }
        this.name = name;
        isDone = false;
    }

    Todo(){
        this.name = "";
        isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " ");
    }

    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + name;
    }
}
