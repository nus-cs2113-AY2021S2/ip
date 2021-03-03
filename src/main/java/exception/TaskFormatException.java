package exception;

public class TaskFormatException extends RuntimeException{
    protected final String PRE_SPACE = "    ";

    public TaskFormatException(){};

    @Override
    public String toString(){
        return new String("Input format:\n    todo todo name\n    deadline deadline name /by time of deadline" +
                "\n    event event name /at time of the event");
    }
}
