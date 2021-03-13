package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents a deadline Task
 */
public class DeadLines extends Task {

    private final String dateAndTime;

    /**
     * @param task contains the task
     * @param deadline contains the date/time
     */
    public DeadLines(String task, String deadline) {
        super(task);
        this.dateAndTime = deadline;
    }

    /**
     * Prints the task with its current status in addition with the specified time format
     * @throws DateTimeParseException if wrong date format is entered. It will then just prints
     * whatever date and time the user inputted.
     * */
    @Override
    public void printStatus() {
        System.out.print("[D]");
        super.printStatus();
        try {
            String[] words = dateAndTime.split(" ");
            String date = words[0];
            String time = words[1];
            LocalDate d1 = LocalDate.parse(date);
            System.out.println("(by: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " + time + ")");
        } catch (DateTimeParseException e) {
            System.out.println("(by: " + dateAndTime + ")");
        }
    }
}
