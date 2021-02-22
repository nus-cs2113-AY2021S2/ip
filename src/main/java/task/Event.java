package task;

/**
 * Event class for handling the event task type.
 */
public class Event extends Task {
    private static final String TASK_SYMBOL_EVENT = "E";
    private String taskTiming;

    /**
     * Event constructor.
     *
     * @param taskDescription contains the task description of the task.
     * @param taskTiming contains the task timing of the task.
     */
    public Event(String taskDescription, String taskTiming) {
        super(taskDescription);
        this.taskTiming = taskTiming;
    }

    /**
     * Return the event task type.
     *
     * @return the event task type.
     */
    @Override
    public String getTaskSymbol() {
        return TASK_SYMBOL_EVENT;
    }

    /**
     * Return the event task timing.
     *
     * @return the event task timing.
     */
    @Override
    public String getTaskTiming() {
        return this.printTaskTiming();
    }

    /**
     * Return the event task timing.
     *
     * @return the event task timing.
     */
    public String printTaskTiming() {
        return "(at: " + this.taskTiming + ")";
    }

    /**
     * Return the task information for writing to hard disk.
     *
     * @return a string in correct file format for writing.
     */
    @Override
    public String getFileFormat() {
        return super.getFileFormat() + "| " + taskTiming;
    }
}
