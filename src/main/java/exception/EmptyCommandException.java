package exception;

/**
 * Represents a custom checked exception that occurs when the description of the command is empty.
 */
public class EmptyCommandException extends Throwable{
    private final String command;
    public EmptyCommandException(String command) {
        this.command = command;
    }
    @Override
    public String getMessage() {
        return "____________________________________________________________\n" +
                "The description of a " + command + " cannot be empty!\n" +
                "____________________________________________________________\n";
    }
}
