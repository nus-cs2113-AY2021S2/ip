package task.list;


/**
 * Represents a deadline
 */

import arthur.date.ArthurDate;


public class Deadline extends Todo {
    private static final String TASK_TITLE = "D";
    public static final int BY_INDEX = 1;
    public static final int DESCRIPTION_INDEX = 0;
    public static final String NO_BY = "No Deadline!! Hehe! :)";
    public static final String NEW_DEADLINE = "Aight Crewmate!! I've got a new deadline for you!!! ( ^ _ ^ ): ";
    public static final String BY_HEADER = "(by: ";
    public static final int NUMBER_OF_ATTRIBUTES = 2;
    public static final String DELIMITER = "//";
    public static final String EMPTY_STRING = "";
    protected String by;


    /**
     * creates a task which has a deadline
     *
     * @param line takes in user input
     */

    public Deadline(String line) {
        this.isDone = false;
        this.description = getDescription(line);
        this.by = getBy(line);
        printAddedTask();
    }

    /**
     * gets description from the user input
     *
     * @param line command line
     * @return description from the command line
     */
    private String getDescription(String line) {
        String[] description = line.split(DELIMITER, NUMBER_OF_ATTRIBUTES);
        return description[DESCRIPTION_INDEX].trim();
    }

    /**
     * returns by
     *
     * @return by attribute
     */
    public String getTaskBy() {
        return this.by;
    }

    /**
     * gets by from the user input
     *
     * @param line takes in user input
     * @return by from the inputted line
     */
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
        return by.trim();
    }

    @Override
    public void printTask() {
        String phrase = "[" + TASK_TITLE + "]" + "[" + getStatusIcon() + "]" + SPACE
                + getTaskDescription() + SPACE + BY_HEADER
                + getTaskBy() + ")";
        System.out.println(phrase);
    }

    @Override
    public void printAddedTask() {
        System.out.println(NEW_DEADLINE + getTaskDescription());
    }

    @Override
    public String getTaskToPrintInFile() {
        return ("deadline " + this.description + DELIMITER + ArthurDate.reformatOutput(this.by) + System.lineSeparator()
                + this.isDone + System.lineSeparator());
    }
}