package duke;

/**
 * Represents a task with a specific deadline. A Deadline object contains the
 * task descriptions, state, and some basic manipulations of the information
 * in the task such as printing the task information.
 */
public class Deadline extends Task {
    private String deadline;
    private String keyWordBeforeDeadline;

    /**
     * Constructor of the Deadline class
     *
     * @param description unformatted textual description of a task
     */
    public Deadline(String description) {
        super(description);
        preprocessInput(description);
    }

    /**
     * Breaks down the unformatted textual description of a task into formatted, useful
     * segments(such as extracting the task description and the date and time of the deadline,
     * and stores the processed information into different variables in the class.
     *
     * @param input unformatted textual description of a task
     */
    private void preprocessInput(String input){
        int indexOfBackslash = input.indexOf("/");
        this.description = input.substring(0,indexOfBackslash-1);
        String stringAfterBackslash = input.substring(indexOfBackslash+1);
        int indexFirstSpaceAfterBackslash = stringAfterBackslash.indexOf(" ");
        this.keyWordBeforeDeadline =
                stringAfterBackslash.substring(0, indexFirstSpaceAfterBackslash);
        this.deadline = stringAfterBackslash.substring(indexFirstSpaceAfterBackslash + 1);
    }

    /**
     * Returns the specific deadline of the task in String format
     *
     * @return Deadline of the task
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Returns the keyword before a specific deadline.
     * For example, returns by in "by 12/9/2022 14:00"
     *
     * @return keyword before a specific deadline (eg "by" in "by 12/9/2022 14:00")
     */
    public String getKeyWordBeforeDeadline() {
        return keyWordBeforeDeadline;
    }

    /**
     * Prints the information of the task
     */
    @Override
    public void printTask() {
        System.out.printf("[D][%s] %s  (%s: %s)\n",
                super.getStatusIcon(), super.getDescription().substring(9),
                getKeyWordBeforeDeadline(), getDeadline());
    }
}
