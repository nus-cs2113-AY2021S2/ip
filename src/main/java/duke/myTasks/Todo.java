package duke.myTasks;
import duke.myExceptions.NoContent;

/**
 * The base class for tasks.
 *
 */
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

    /**
     * @return String representation of the class for use by the list.printList() method and the Save class
     */
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + name;
    }
}
