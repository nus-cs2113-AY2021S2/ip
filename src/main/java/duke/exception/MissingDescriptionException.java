package duke.exception;

import duke.command.CommandType;

/**
 * Exception for user input commands with missing description for the task
 */
public class MissingDescriptionException extends DukeException {
    private CommandType commandType;

    public MissingDescriptionException(CommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public String toString() {
        return super.toString() + " The description of a " + commandType.name() + " cannot be empty.";
    }
}
