

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the Event class which is used to deal with commands that start with Event.
 * This also includes the time and date function.
 */

public class Event extends Task {
    LocalDateTime date;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hhmm");

    /**
     * Constructor used to instantiate a new Event object.
     *
     * @param date A localdatetime object is obtained from the user's input.
     * @param work This is a String variable which has the the actual description of the Event task.
     */

    public Event(String work, LocalDateTime date){
        super(work, date);
        this.date = date;
    }

    /**
     * Gets the formatted version of the date and time
     *
     * @return A formatted String version of the date and time.
     */

    public String toString(){
        return "[E]" + super.toString() + "(at: " + date.format(format) + ")";
    }

    /**
     * This method will get the actual description of the Event task
     *
     * @return This will return a string which has the actual description.
     */

    public String description(){
        return super.isDone()
                ? "[E][✓]"
                : "[E][✗]";
    }
}

