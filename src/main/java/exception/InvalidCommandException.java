package exception;

public class InvalidCommandException extends RuntimeException{
    public final static String PRE_SPACE = "    ";

    public InvalidCommandException(){}

    @Override
    public String toString(){
        return new String("Duke only accepts the following commands:\n" +
                PRE_SPACE + "1.todo + todo name\n" + PRE_SPACE +
                "2.deadline + deadline name + /by + time of deadline\n" + PRE_SPACE +
                "3.event + event name + /at + time of the event\n" + PRE_SPACE +
                "4.done + task index\n" + PRE_SPACE +
                "5.delete + task index");
    }
}
