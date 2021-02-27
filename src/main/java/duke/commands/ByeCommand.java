package duke.commands;

public class ByeCommand extends Command {

    public ByeCommand() {
        super.shouldExit = true;
    }

    @Override
    public void execute() {
        ui.printGoodbye();
    }
}
