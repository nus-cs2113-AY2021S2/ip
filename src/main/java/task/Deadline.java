package task;

/**
 * Deadline class for handling the deadline task type.
 */
public class Deadline extends Task {
    private static final String TASK_SYMBOL_D = "D";
    private String taskTiming;

    /**
     * Deadline constructor.
     *
     * @param taskDescription contains the task description of the task.
     * @param taskTiming contains the task timing of the task.
     */
    public Deadline(String taskDescription, String taskTiming) {
        super(taskDescription);
        this.taskTiming = taskTiming;
    }

    /**
     * Return the deadline task type.
     *
     * @return the deadline task type.
     */
    @Override
    public String getTaskSymbol() {
        return TASK_SYMBOL_D;
    }

    /**
     * Return the deadline task timing.
     *
     * @return the deadline task timing.
     */
    @Override
    public String getTaskTiming() {
        return this.printTaskTiming();
    }

    /**
     * Return the deadline task timing.
     *
     * @return the deadline task timing.
     */
    public String printTaskTiming() {
        return "(by: " + this.taskTiming + ")";
    }

    /**
     * Return the output format for writing to hard disk.
     *
     * @return a string in correct file format for writing.
     */
    @Override
    public String getFileFormat() {
        return super.getFileFormat() + "| " + taskTiming;
    }
}
