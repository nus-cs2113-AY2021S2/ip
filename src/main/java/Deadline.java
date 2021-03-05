

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the Deadline class which is used to tasks that start with deadline.
 * this also includes the time and date function.
 */
public class Deadline extends Task {
    LocalDateTime deadline;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hhmm");

    public Deadline(String work, LocalDateTime date){
        super(work, date);
        this.deadline = date;
    }

    public String description(){
        return super.isDone()
                ? "[D][✓]"
                : "[D][✗]";
    }
    // prints the formatted version of the date and time.
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline.format(format) + ")";
    }
}
