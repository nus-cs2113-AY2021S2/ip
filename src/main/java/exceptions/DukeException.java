package exceptions;

import tasks.TaskType;

/**
 * Represents exceptions specific to the Duke application.
 */
public class DukeException extends Exception {
    private final TaskType TASKTYPE;


    public DukeException(TaskType taskType) {
        this.TASKTYPE = taskType;
    }

    /**
     * Print error message when the user does not indicate task description while adding task.
     */
    public void getErrorTaskCannotBeEmpty() {
        System.out.println("OOPS!!! The description of a " + TASKTYPE.toString().toLowerCase() + " cannot be empty.");
    }

    /**
     * Print error message when the user does not indicate search description while finding tasks.
     */
    public void getErrorSearchCannotBeEmpty() {
        System.out.println("OOPS!!! The search description cannot be empty. ");
    }

}
