package Task;

/**
 * Keeps track of a Task without any time attached
 */

public class Todo extends Task{
    public Todo(String label) {
        super(label);
    }

    @Override
    public String getType() {
        return "[T]";
    }
}
