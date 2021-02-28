package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deals with deadline task which consists of deadline.
 */
public class Deadline extends Task {
    /**
     * Deadline of the task.
     */
    private LocalDateTime by;

    /**
     * Constructs new deadline task.
     *
     * @param description Description of the new deadline task.
     * @param by Deadline of the new deadline task.
     * @throws DukeException If the format of deadline is wrong.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        setBy(by);
    }

    /**
     * Format the deadline from user input into LocalDateTime object and assign the value.
     *
     * @param by Deadline string from user input.
     * @throws DukeException If the deadline format from user input is in wrong format.
     */
    public void setBy(String by) throws DukeException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(by, dateTimeFormatter);
            this.by = dateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("Date or time format is incorrect.\n" +
                    "Correct format: 2021-02-04 22:59");
        }
    }

    /**
     *
     * @return Formatted deadline.
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mma"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getBy() + ")";
    }
}