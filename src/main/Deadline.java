package main;

/**
 * Subclass of Task object, requires deadline date/time.
 *
 * @author Jeremy Teo
 * @version 0.2
 * @since 2021-02-28
 */
public class Deadline extends Task {

    private final String dateBy;

    /**
     * Deadline object constructor.
     *
     * @param task string description of task
     * @param dateBy string date/time of when task is due
     */
    public Deadline(String task, String dateBy) {
        super(task);
        this.dateBy = dateBy;
    }

    /**
     * Get status of the deadline task.
     *
     * @return string of the deadline task completion status
     */
    @Override
    public String getStatusIcon() {
        return (isDone ? "[D][\u2713]" : "[D][\u2718]"); //return tick or X symbols
    }

    /**
     * Get the description of the deadline task.
     *
     * @return formatted string of the task.
     */
    @Override
    public String getDescription() {
        String deadlineDescription = String.format("%s (by: %s)", description, dateBy);
        return deadlineDescription;
    }



}
