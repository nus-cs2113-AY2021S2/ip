package duke.tasks;

/**
 * Represents a task of type {@code Deadline}.
 * Deadlines have an additional field {@code due} which refers to the deadline of a particular task.
 */
public class Deadline extends Task{

    protected String due;

    public Deadline(String inputJob, String due) {
        super(inputJob);
        this.due = due;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    /** Label: [D] */
    @Override
    public String addLabel(String s) {
        String label = "[D]";
        label += s;
        return label;
    }

    /**
     * String representation of a {@code Deadline} object is suffixed with the due date/time.
     */
    @Override
    public String addEnd(String s){
        String end = " (" + "by: " + this.due + ")";
        return s.concat(end);
    }
}
