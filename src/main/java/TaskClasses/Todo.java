package TaskClasses;

public class Todo extends Task{
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T" + super.toSaveFormat();
    }
}
