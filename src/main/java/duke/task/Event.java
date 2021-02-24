package duke.task;

import duke.exception.MissingEventDurationException;
import duke.exception.MissingTaskDescriptionException;

/**
 * Event class builds upon the Deadline class; they both have a duedate field
 */
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
