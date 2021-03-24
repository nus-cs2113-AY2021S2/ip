package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidInputException;
import duke.exception.UnmatchedListException;

/**
 * Represent a invalid command entered the user.
 */
public class InvalidCommand extends Command {
    private String commandWord;
    private String description;
    private Exception error;

    /**
     * Construct InvalidCommand using the commandWord, description and the exception occur.
     *
     * @param commandWord the command that error occur.
     * @param description the description that error occur.
     * @param exception the error type that has occurred.
     */
    public InvalidCommand(String commandWord, String description, Exception exception) {
        this.commandWord = commandWord;
        this.description = description;
        this.error = exception;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(invalidCommandMessage());
    }

    private String invalidCommandMessage() {
        String message;
        if (error instanceof NumberFormatException) {
            message = "☹ OOPS!!! The description of " + commandWord + " can only be numeric.\n";
        } else if (error instanceof IndexOutOfBoundsException) {
            message = "☹ OOPS!!! The number " + description + " is not in the list.\n";
        } else if (error instanceof InvalidInputException) {
            message = "☹ OOPS!!! The description of " + commandWord + " must comes with a date.\n";
        } else if (error instanceof EmptyDescriptionException) {
            message = "☹ OOPS!!! The description of " + commandWord + " cannot be empty.\n";
        } else if (error instanceof UnmatchedListException) {
            message = "☹ OOPS!!! There is no matching task in the list.\n";
        } else {
            message = "☹ OOPS!!! Sorry I don't understand what you mean.\n";
        }
        return message;
    }
}
