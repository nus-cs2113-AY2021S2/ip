package duke.command;

/**
 * Represent a command to exit the application.
 */
public class ExitCommand extends Command {
    public static final String EXIT_COMMAND = "bye";

    @Override
    public CommandResult execute() {
        setExitCommand();
        return new CommandResult(exitMessage());
    }

    protected void setExitCommand () {
        this.isExit = true;
    }

    protected String exitMessage () {
        String message = "Exiting task tracker...\n";
        return message;
    }
}
