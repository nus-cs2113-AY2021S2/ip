package duke.tasks;

/**
 * Represents a task of type {@code Todo}.
 * The most basic type of task, with only a task description field.
 */
public class Todo extends Task{

    public Todo(String inputJob) {
        super(inputJob);
    }

    /** Label: [T] */
    @Override
    public String addLabel(String s) {
        String label = "[T]";
        label += s;
        return label;
    }

    /** No suffix required for {@code Todo}. */
    @Override
    public String addEnd(String s) {
        return s.concat("");
    }
}
