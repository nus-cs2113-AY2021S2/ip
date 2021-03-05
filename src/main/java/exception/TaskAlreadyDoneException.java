package exception;

/**
 * Represents the Exception that occurs when the user wants to mark a task that is done before as done again.
 */
public class TaskAlreadyDoneException extends RuntimeException{

    public TaskAlreadyDoneException(){}

    @Override
    public String toString(){
        return "The task is already marked as done";
    }
}
