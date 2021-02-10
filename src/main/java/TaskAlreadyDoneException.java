public class TaskAlreadyDoneException extends RuntimeException{
    public TaskAlreadyDoneException(String s){
        super(s);
    }

    public TaskAlreadyDoneException(){};
}
