package duke.task;

import duke.exception.MissingDueDateException;
import duke.exception.MissingTaskDescriptionException;

/**
 * This class is for Tasks containing a due date
 */
public class Deadline extends Task {
    protected String dueDate;

    /**
     * Deadline constructor method
     * @param task is the task description
     * @param dueDate is he task due date - specific to deadlines
     */
    public Deadline(String task, String dueDate) {
        super(task);
        this.dueDate = dueDate;
    }

    public String getDateTime(){
        return this.dueDate;
    }

    public String toBaseString(){
        return super.toString();
    }

    @Override
    public String toString() {
        return "Deadline : " + toBaseString() + " || Due by: " + this.getDateTime();
    }

    // Exceptions

    public static void checkDeadlineInput(String[] taskDetails) throws MissingDueDateException, MissingTaskDescriptionException {
        if (taskDetails[0] == null){
            throw new MissingTaskDescriptionException();
        }
        if (taskDetails[1] == null) {
            throw new MissingDueDateException();
        }

    }
}
