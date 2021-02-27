package duke.Exceptions;

public class CommandNotFoundException extends DukeException {

    public CommandNotFoundException() {
        this.errorMessage = "You have entered an unknown command";
    }
}
