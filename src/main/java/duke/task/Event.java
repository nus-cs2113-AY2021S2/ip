package duke.task;

public class Event extends Task {

    public static final String TASK_TYPE = "E";

    private String schedule;

    public Event(String taskName, String schedule) {
        super(taskName);
        this.schedule = schedule;
    }

    public String getSchedule() {
        return schedule;
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (at: " + schedule + ")";
    }
}
