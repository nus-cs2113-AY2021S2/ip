package Duke.Task;

import Duke.Constant.TaskType;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String getType() {
        return TaskType.DEADLINE_TYPE;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " +
                description + " (by: " + getDeadline() + ")";
    }

    @Override
    public String toPrintedFormat() {
        return TaskType.DEADLINE_TYPE + " | " + super.getIntegerType() + " | " + description + " | " + getDeadline();
    }

    @Override
    public String getDate() {
        return getDeadline();
    }
}