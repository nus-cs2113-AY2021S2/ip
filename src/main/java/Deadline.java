

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the Deadline class which is used to deal with commands that start with deadline.
 * This also includes the time and date function.
 */
public class Deadline extends Task {
    LocalDateTime deadline;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hhmm");

    /**
     * Constructor used to instantiate a new deadline object.
     *
     * @param date A localdatetime object is obtained from the user's input.
     * @param work This is a String variable which has the the actual description of the deadline task.
     */

    public Deadline(String work, LocalDateTime date){
        super(work, date);
        this.deadline = date;
    }

    /**
     * This method will get the actual description of the deadline task
     *
     * @return This will return a string which has the actual description.
     */

    public String description(){
        return super.isDone()
                ? "[D][✓]"
                : "[D][✗]";
    }

    /**
     * Gets the formatted version of the date and time
     *
     * @return A formatted String version of the date and time.
     */

    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline.format(format) + ")";
    }
}
