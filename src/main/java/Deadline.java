public class Deadline extends Task {

    public static final String TASK_TYPE = "D";

    private String dueDay;

    public Deadline(String taskName, String dueDay) {
        super(taskName);
        this.dueDay = dueDay;
    }

    public String getDueDay() {
        return dueDay;
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (by: " + dueDay + ")";
    }
}
