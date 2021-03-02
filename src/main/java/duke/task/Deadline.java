package duke.task;

public class Deadline extends Task {

    protected String by;

    public static final String DEADLINE_LABEL = "[D]";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public void checkIfDeadlineDescriptionExists(String input) throws TaskDescriptionMissingException {
        if (input.isBlank()) {
            Task.decreaseTaskCount();
            throw new TaskDescriptionMissingException();
        }
    }


    @Override
    public String toString() {
        return DEADLINE_LABEL + super.toString() + "(by: " + by + ")";
    }
}

