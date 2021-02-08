/* to handle exceptions specific to Duke */

public class DukeException extends Exception {

    private Duke.TaskType taskType;
    public DukeException(Duke.TaskType taskType) {
        this.taskType = taskType;
    }
    public void printErrorTaskCannotBeEmpty() {
        System.out.println("OOPS!!! The description of a " + taskType + " cannot be empty.");
    }

}
