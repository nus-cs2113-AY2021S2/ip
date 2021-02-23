package exceptions;


import tasks.TaskType;

public class DukeException extends Exception {
    private final TaskType TASKTYPE;

    public DukeException(TaskType taskType) {
        this.TASKTYPE = taskType;
    }

    public void getErrorTaskCannotBeEmpty() {
        System.out.println("OOPS!!! The description of a " + TASKTYPE.toString().toLowerCase() + " cannot be empty.");
    }

    public void getErrorSearchCannotBeEmpty() {
        System.out.println("OOPS!!! The search description cannot be empty. ");
    }

}
