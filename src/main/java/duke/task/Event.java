package duke.task;

import duke.parser.Parser;

import java.time.LocalDate;

/**
 * Represents a task that happens at a given period of time.
 */
public class Event extends Task {
    private LocalDate eventTime;

    /**
     * Creates an event task object with specified task content and time period.
     *
     * @param content the content of the event.
     * @param period  the period when the event takes place.
     */
    public Event(String content, LocalDate period) {
        super(content);
        this.taskType = TaskType.EVENT;
        this.eventTime = period;
    }

    /**
     * Checks if a user input contains keyword used to identify should a event task be created.
     *
     * @param userInput User's keyboard input in String.
     * @throws Exception an exception object with error message to notify the user that
     *                   the event object cannot be create due to the absence of keywords.
     */
    public static void isEventCommandValid(String userInput) throws Exception {
        if (!userInput.contains("/at")) {
            throw new Exception("Invalid event command. Check 'help'.\n");
        }
    }

    /**
     * Returns the content of the event task.
     *
     * @param userInput User's keyboard input in String.
     * @return a String of the content of the event task in a usable format.
     * @throws Exception an exception object with error message to notify the user that
     *                   the task content cannot be found or parsed.
     */
    public static String[] getEventTaskContent(String userInput) throws Exception {
        String[] words = userInput.split(" ");
        int indexOfAtWord = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/at")) {
                indexOfAtWord = i;
                break;
            }
        }
        return Parser.parseTaskContentAndTime(words, indexOfAtWord);
    }

    /**
     * Gets the time period of the event task.
     *
     * @return a LocalDate object representing the time period.
     */
    public LocalDate getEventTime() {
        return eventTime;
    }

    /**
     * Gets the time period of the event task in String.
     *
     * @return a String of the time period.
     */
    public String getTimeLimitString() {
        return eventTime.toString();
    }
}
