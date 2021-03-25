package duke.items;

import static duke.main.UI.convertDateToStringFormat;

/**
 * Deadline class
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Deadline constructor
     *
     * @param description Description of the task
     * @param byInput input String of date
     */
    public Deadline(String description, String byInput) {
        super(description);
        this.by = byInput;
    }

    /**
     * Change the by attribute of Deadline
     *
     * @param byInput new String of date
     */
    public void setBy(String byInput) {
        this.by = byInput;
    }

    /**
     * Returns the by attribute of Deadline object
     *
     * @return this.by
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns the Type of Deadline object
     *
     * @return String "D"
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Prints the Deadline object in a certain format
     */
    @Override
    public void print(){
        if (this.isDone) {
            System.out.println("[D][√] " + description + " (by: " + by + ")");
        } else {
            System.out.println("[D][X] " + description + " (by: " + by + ")");
        }
    }
}
