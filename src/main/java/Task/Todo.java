package Task;

/***
 * Represents a task that wants to be done in the task list.
 */

public class Todo extends Task {


    public Todo(String description) {
        super(description);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
