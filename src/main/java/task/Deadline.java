package task;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Deadline constructor method
     *
     * @param description description refers to task description
     * @param date        date is the task(Deadline) due date
     * @param time        time is the time due date for the task(Deadline)
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Accessor Method
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Accessor Method
     * @return time
     */
    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + " " + time + ")";
    }

}

