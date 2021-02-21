/**
 * Child class Todo inherited from parent class Task
 */
public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    /**
     * Change to proper format
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
