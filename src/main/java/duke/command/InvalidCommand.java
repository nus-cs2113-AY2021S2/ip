package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.exception.EmptyStringException;

public class InvalidCommand extends Command {
    private String commandWord;
    private String description;
    private Exception error;

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
        } else if (error instanceof EmptyStringException) {
            message = "☹ OOPS!!! The description of " + commandWord + " must comes with a date.\n";
        } else if (error instanceof EmptyDescriptionException) {
            message = "☹ OOPS!!! The description of " + commandWord + " cannot be empty.\n";
        } else {
            message = "☹ OOPS!!! Sorry I don't understand what you mean.\n";
        }
        return message;
    }
}
