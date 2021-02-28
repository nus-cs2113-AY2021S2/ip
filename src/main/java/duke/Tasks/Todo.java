package duke.Tasks;

import java.time.LocalDateTime;

/**
 * Extending the <code>Task</code> class that represents a basic <code>Todo</code> without time objects
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public LocalDateTime getDateTime() {
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
