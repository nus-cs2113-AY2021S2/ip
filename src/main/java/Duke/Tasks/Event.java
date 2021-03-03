package Duke.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    LocalDate eventDate;

    public Event(String nameInit, LocalDate eventDate) {
        super(nameInit);
        this.eventDate = eventDate;
    }

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

    public LocalDate getDate() {
        return eventDate;
    }
}
