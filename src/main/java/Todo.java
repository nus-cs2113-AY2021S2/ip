public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getSaveFormatString() {
        return "T | " + super.getSaveFormatString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
