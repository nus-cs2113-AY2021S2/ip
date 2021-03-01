package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    public Deadline(String item, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(item);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.print(" (by: " + this.getDeadline() + ")");
    }

    @Override
    public void printCondensedTask() {
        super.printCondensedTask();
        System.out.print(" (by: " + this.getFormattedDeadlineTime() + ")");
    }

    @Override
    public String toString() {
        return super.toString() + " | "
                + this.getDeadlineDate() + " | "
                + this.getDeadlineTime();
    }

    public String getDeadline() {
        String formattedDate = getFormattedDeadlineDate();
        String formattedTime = getFormattedDeadlineTime();
        return formattedDate + ", " + formattedTime;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public String getFormattedDeadlineDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return deadlineDate.format(formatter);
    }

    public LocalTime getDeadlineTime() {
        return deadlineTime;
    }

    public String getFormattedDeadlineTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h.mm a");
        return deadlineTime.format(formatter);
    }
}
