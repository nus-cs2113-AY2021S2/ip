package Duke.Tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.toString();
    }

    @Override
    public String saveTask() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}