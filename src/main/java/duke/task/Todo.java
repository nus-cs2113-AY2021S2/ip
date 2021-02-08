package duke.task;

public class Todo extends Task {
    
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[TD]" + super.toString();
    }
}
