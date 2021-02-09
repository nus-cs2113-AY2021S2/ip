public class Todo extends Task{

    public static final String TODO_LABEL = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TODO_LABEL + super.toString();
    }
}
