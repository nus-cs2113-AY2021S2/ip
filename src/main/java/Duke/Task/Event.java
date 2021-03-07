package Duke.Task;

import Duke.Constant.TaskType;

public class Event extends Task {
    protected String period;

    public Event(String description, String period) {
        super(description);
        this.period = period;
    }

    public Event(String description, String period, boolean isDone) {
        super(description, isDone);
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    @Override
    public String getType() {
        return TaskType.EVENT_TYPE;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " +
                description + " (at: " + getPeriod() + ")";
    }

    @Override
    public String toPrintedFormat() {
        return TaskType.EVENT_TYPE + " | " + super.getIntegerType() + " | " + description + " | " + getPeriod();
    }

    @Override
    public String getDate() {
        return getPeriod();
    }
}