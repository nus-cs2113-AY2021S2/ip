package ip.duke.task;

/**
 * Represents a type of task with description only
 * without any date/time
 * status is undone by default unless user marks it as done
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toDataString() {
        return "T" + super.toDataString();
    }

    public String getDate() {
        return null;
    }
}
