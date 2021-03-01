package duke.commands;

/**
 * Represents a command that displays a farewell message and
 * terminates the program when executed.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        super.shouldExit = true;
    }

    @Override
    public void execute() {
        ui.printGoodbye();
    }
}
