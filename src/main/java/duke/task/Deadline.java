package duke.task;

/*
Class Deadline for creating deadline tasks
*/
public class Deadline extends Task {

    protected String deadlineDate;

    /*
    Constructor for Deadline Object
    Initialize description, taskType,deadlineDate variables
    */
    public Deadline(String description, String deadlineDate) {
        super(description, 'D');
        this.deadlineDate = deadlineDate;
    }

    /*
    Overrides toString() method from superclass
    Returns a string of task details
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate + ")";
    }

    /*
    Returns deadline date
     */
    public String getDeadlineDate(){
        return deadlineDate;
    }
}