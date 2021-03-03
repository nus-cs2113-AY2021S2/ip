package exception;

public class TaskAlreadyDoneException extends RuntimeException{

    public TaskAlreadyDoneException(){}

    @Override
    public String toString(){
        return new String("The task is already marked as done");
    }
}
