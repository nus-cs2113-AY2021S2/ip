package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents a task with deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline.
     * @param description the description of the task with deadline
     * @param deadline a LocalDateTime object with the date of the deadline
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Alternative constructor for Deadline. Creates Deadline from existing information.
     * @param description the description of the task with deadline
     * @param isDone the completion status of the task
     * @param deadline a LocalDateTime object with the date of the deadline
     */
    public Deadline(String description, boolean isDone, LocalDateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * @return LocalDateTime object with the date and time of the deadline
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * @return string for command line output
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) +")";
    }

    @Override
    public String toFileOutput() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
