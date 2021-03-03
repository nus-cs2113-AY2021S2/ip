package Duke.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /**
     * Date of the event
     */
    LocalDate eventDate;

    /**
     * Constructor for the event class
     * @param nameInit
     * @param eventDate
     */
    public Event(String nameInit, LocalDate eventDate) {
        super(nameInit);
        this.eventDate = eventDate;
    }

    /**
     *
     * @return the string to be saved by the program representing the different attributes of the task
     */
    @Override
    public String toString() {
        String outputString = "[E]";
        if (isDone) {
            outputString += "[\u2713]";
        }
        else {
            outputString += "[\u2715]";
        }
        DateTimeFormatter formatObject = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedStartTime = eventDate.format(formatObject);
        outputString = outputString + " " + name + " (at: " + formattedStartTime + ")";
        return outputString;
    }

    /**
     *
     * @return the string to be saved by the program representing the different attributes of the task
     */
    public String toStringSave() {
        String saveString = "E | ";
        if (isDone) {
            saveString += "1 | ";
        }
        else {
            saveString += "0 | ";
        }
        saveString = saveString + name + " | " + eventDate;
        return saveString;
    }

    /**
     *
     * @return the date of the event
     */
    public LocalDate getDate() {
        return eventDate;
    }
}
