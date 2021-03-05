package Classes;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String formatString() {
        int done = (isDone ? 1 : 0);
        return "T-" + done + "-" + description + "\n";
    }
}
