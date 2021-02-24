package Task;

public class Todo extends Task{
    public Todo(String label) {
        super(label);
    }

    @Override
    public String getTypeString() {
        return "[T]";
    }
}
