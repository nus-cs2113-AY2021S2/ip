package exception;

/**
 * Represents a custom checked exception that occurs when the command is invalid due to invalid description.
 */
public class InvalidCommandException extends Throwable {
    private final String command;
    public InvalidCommandException(String command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        if (command.equals("deadline")) {
            return "____________________________________________________________\n" +
                    "The description of a " + command + " must be in the form 'deadline /by time'\n" +
                    "____________________________________________________________\n";
        } else if (command.equals("event")) {
            return "____________________________________________________________\n" +
                    "The description of a " + command + " must be in the form 'event /at time!\n" +
                    "____________________________________________________________\n";
        } else {
            return super.getMessage();
        }
    }
}
