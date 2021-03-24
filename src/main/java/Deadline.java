import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * setting a task as deadline
 */
public class Deadline extends Task{

    public LocalDate by;

    /**
     * Deadline Constructor
     * @param description task name
     * @param by date of deadline
     */
    public Deadline(String description , LocalDate by){
        super(description);
        this.by = by;
    }

    /**
     * format of date
     * @return output date in format of MMM dd yyy
     */
    public String dateTimeFormat(){
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }


    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.dateTimeFormat() + ")";
    }

    @Override
    public String write(){
        return "D|" + this.getStatusIcon() + "|" + this.description + "|" + this.by;
    }
}