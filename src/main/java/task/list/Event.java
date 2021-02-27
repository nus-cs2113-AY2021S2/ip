package task.list;

import arthur.date.ArthurDate;

/**
 * Represents an Event
 */
public class Event extends Deadline {
    private static final String TASK_TITLE = "E";
    public static final int BY_INDEX = 1;
    public static final String NEW_EVENT = "Aight Crewmate!! I've got a new event for you!!! ( ^ _ ^ ): ";
    public static final String COMMAND_HEADER = "event ";
    public static final String BY_HEADER = "(by: ";
    public static final String NO_BY = "No Event time!! Noice!!! :)";

    /**
     * creates an event
     *
     * @param line takes in user input
     */
    public Event(String line) {
        super(line);
    }

    @Override
    public String getBy(String line) {
        String[] lineWords = line.split(DELIMITER, NUMBER_OF_ATTRIBUTES);
        String by;
        try {
            by = lineWords[BY_INDEX];
            if (by.equals(EMPTY_STRING)) {
                by = NO_BY;
            } else {
                by = ArthurDate.reformatInput(by);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            by = NO_BY;
        }
        return by;
    }

    @Override
    public void printTask() {
        String phrase = "[" + TASK_TITLE + "]" + "[" + getStatusIcon() + "]" + SPACE
                + getTaskDescription() + " " + BY_HEADER
                + getTaskBy() + ")";
        System.out.println(phrase);
    }

    @Override
    public void printAddedTask() {
        System.out.println(NEW_EVENT + getTaskDescription());
    }

    @Override
    public String getTaskToPrintInFile() {
        return (COMMAND_HEADER + this.description + DELIMITER + ArthurDate.reformatOutput(this.by) +
                System.lineSeparator() + this.isDone + System.lineSeparator());
    }
}