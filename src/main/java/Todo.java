public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getSaveString() {
        return "T | " + super.getSaveString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
