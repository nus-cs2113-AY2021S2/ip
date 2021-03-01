package duke.commands;

/**
 * Represents a command that displays a message explaining why given input is invalid.
 */
public class InvalidCommand extends Command {
    private final String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute() {
        ui.printErrorMessage(errorMessage);
    }
}
