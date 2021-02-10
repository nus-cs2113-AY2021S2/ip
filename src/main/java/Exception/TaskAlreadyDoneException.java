package Exception;

public class TaskAlreadyDoneException extends RuntimeException{
    public TaskAlreadyDoneException(String s){
        super(s);
    }

    public TaskAlreadyDoneException(){};

    @Override
    public String toString(){
        return new String("The task is already marked as done");
    }
}
