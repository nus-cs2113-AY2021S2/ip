package duke.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "T";
    }

    public String getDetails() {
        return "[" + getType() + "][" + getStatusIcon() + "] " + getDescription();
    }
}
