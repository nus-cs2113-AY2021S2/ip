package Duke.Tasks;

import java.time.LocalDate;

/**
 * Abstract Task class to be extended by all other Task classes
 */
public abstract class Task {

    /**
     * Name of the task
     */
    protected String name;

    /**
     * Marker to check if the task is done
     */
    protected boolean isDone = false;

    /**
     * Constructor that sets the name of the task
     * @param nameInit name of the task
     */
    public Task (String nameInit) {
        this.name = nameInit;
    }

    /**
     * Sets the task as done
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Gets the name of the task
     * @return the name of the task
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the string to be saved by the program representing the different attributes of the task
     */
    public abstract String toStringSave();

    /**
     * gets the date of the task for either the event or deadline task
     * @return the LocalDate object of the date of the task
     */
    public abstract LocalDate getDate();

}
