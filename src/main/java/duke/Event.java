package duke;

/**
 * Represents a task with a specific event time. A Event object contains the
 * task descriptions, state, and some basic manipulations of the information
 * in the task such as printing the task information.
 */
public class Event extends Task {
    private String duration;
    private String keyWordBeforeDuration;

    /**
     * Constructor of the Event class
     *
     * @param description unformatted textual description of a task
     */
    public Event(String description) {
        super(description);
        preprocessInput(description);
    }

    /**
     * Breaks down the unformatted textual description of a task into formatted, useful
     * segments(such as extracting the task description and the event duration),
     * and stores the processed information into different variables in the class.
     *
     * @param input unformatted textual description of a task
     */
    private void preprocessInput(String input){
        int indexOfBackslash = input.indexOf("/");
        this.description = input.substring(0,indexOfBackslash-1);
        String stringAfterBackslash = input.substring(indexOfBackslash+1);
        int indexFirstSpaceAfterBackslash = stringAfterBackslash.indexOf(" ");
        this.keyWordBeforeDuration =
                stringAfterBackslash.substring(0, indexFirstSpaceAfterBackslash);
        this.duration = stringAfterBackslash.substring(indexFirstSpaceAfterBackslash + 1);
    }

    /**
     * Returns the specific duration of the task in String format.
     *
     * @return Duration of the task
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Returns the keyword before a specific duration.
     * For example, returns from in "from 12/9/2022 14:00 to 16:00"
     *
     * @return keyword before a specific duration (eg "from" in "from 12/9/2022 14:00 to 16:00")
     */
    public String getKeyWordBeforeDuration() {
        return keyWordBeforeDuration;
    }

    /**
     * Prints the information of the task
     */
    @Override
    public void printTask() {
        System.out.printf("[E][%s] %s  (%s: %s)\n",
                super.getStatusIcon(), super.getDescription().substring(6),
                getKeyWordBeforeDuration(), getDuration());
    }
}
