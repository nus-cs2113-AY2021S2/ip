package exception;

public class DoneFormatException extends RuntimeException {
    public DoneFormatException(){}

    @Override
    public String toString() {
        return new String("Invalid input format for done command.\n" +
                "    Input format for done: done + task index    eg. done 1");
    }
}
