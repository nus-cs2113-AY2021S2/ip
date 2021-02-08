package duke.exceptions;
import duke.tasks.TaskType;

public class DukeException extends Exception {

    private final TaskType TASKTYPE;
    public DukeException(TaskType taskType) {

        this.TASKTYPE = taskType;
    }



    public void printErrorTaskCannotBeEmpty() {
        System.out.println("OOPS!!! The description of a " + TASKTYPE.toString().toLowerCase() + " cannot be empty.");
    }

}
