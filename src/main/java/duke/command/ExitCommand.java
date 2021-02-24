package duke.command;

public class ExitCommand extends Command {
    public static final String EXIT_COMMAND = "bye";

    @Override
    public CommandResult execute() {
        setExitCommand();
        return new CommandResult(exitMessage());
    }

    public void setExitCommand () {
        this.isExit = true;
    }

    public String exitMessage () {
        String message = "Exiting task tracker...\n";
        return message;
    }
}
