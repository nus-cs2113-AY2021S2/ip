package duke;

public class Todo extends duke.Task {

    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.getDescription();
    }
}
