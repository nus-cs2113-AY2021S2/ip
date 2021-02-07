package duke.task;

import duke.exception.MissingEventDurationException;
import duke.exception.MissingTaskDescriptionException;

public class Event extends Deadline {

    public Event(String description, String eventDuration) {
        super(description, eventDuration);
    }

    public String toString(){
        return "Event : " + super.toBaseString() + " || Happening on: " + this.getDateTime();
    }

    // Exceptions

    public static void checkEventInput(String[] taskDetails) throws MissingEventDurationException, MissingTaskDescriptionException {
        if (taskDetails[0] == null){
            throw new MissingTaskDescriptionException();
        }
        if (taskDetails[1] == null) {
            throw new MissingEventDurationException();
        }

    }

}
