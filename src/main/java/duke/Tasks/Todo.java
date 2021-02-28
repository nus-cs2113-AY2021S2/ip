package duke.Tasks;

import java.time.LocalDateTime;

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
