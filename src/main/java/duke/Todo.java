package duke;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}