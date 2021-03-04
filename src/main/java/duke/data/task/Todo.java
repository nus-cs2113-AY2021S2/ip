package duke.data.task;

/**
 * Represent a to-do item in the task list.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toText(int number) {
        return "T|" + number + "|" + description;
    }
}
