package duke.Exceptions;

/**
 * When user enters a command that is not recognised
 */
public class CommandNotFoundException extends DukeException {

    public CommandNotFoundException() {
        this.errorMessage = "You have entered an unknown command";
    }
}
