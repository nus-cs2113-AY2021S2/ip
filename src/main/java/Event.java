import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * setting a task as event
 */
public class Event extends Task{

    protected LocalDate at;

    /**
     * Event Constructor
     * @param description name of event
     * @param at date of even held
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * format of date
     * @return output date in format of MMM dd yyy
     */
    public String dateTimeFormat(){
        return this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.dateTimeFormat() + ")";
    }

    @Override
    public String write(){
        return "E|" + this.getStatusIcon() + "|" + this.description + "|" + this.at;
    }
}