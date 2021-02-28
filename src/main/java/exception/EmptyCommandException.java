package exception;

import constant.Constants;

/**
 * Represents a custom checked exception that occurs when the description of the command is empty.
 */
public class EmptyCommandException extends Throwable {
    private final String command;

    public EmptyCommandException(String command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        if (command.equals(Constants.STRING_TASK_DEADLINE)) {
            return "____________________________________________________________\n" +
                    "The description and /by of a " + command + " cannot be empty!\n" +
                    "____________________________________________________________\n";
        } else if (command.equals(Constants.STRING_TASK_EVENT)) {
            return "____________________________________________________________\n" +
                    "The description and /at of a " + command + " cannot be empty!\n" +
                    "____________________________________________________________\n";
        } else {
            return "____________________________________________________________\n" +
                    "The description of a " + command + " cannot be empty!\n" +
                    "____________________________________________________________\n";
        }
    }
}
