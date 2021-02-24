package duke.task;

import duke.exception.InvalidDateTimeException;
import duke.exception.MissingEventDurationException;
import duke.exception.MissingTaskDescriptionException;


import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Deadline {

    public Event(String description, String eventDuration) {
        super(description, eventDuration);
    }


    public String toString(){
        return "Event : " + super.toBaseString() + " || Happening on: " + this.printDateTime();
    }

    // Exceptions

    public static void checkEventInput(String[] taskDetails) throws MissingEventDurationException,
            MissingTaskDescriptionException, InvalidDateTimeException {
        try {
            DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
            LocalDateTime.parse(taskDetails[1], parseFormatter);
        } catch (DateTimeException e) {
            throw new InvalidDateTimeException();
        }
        if (taskDetails[0] == null){
            throw new MissingTaskDescriptionException();
        }
        if (taskDetails[1] == null) {
            throw new MissingEventDurationException();
        }
    }

}
