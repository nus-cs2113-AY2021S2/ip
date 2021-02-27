package duke.commands;

public class HelpCommand extends Command {

    @Override
    public void execute() {
        ui.printHelpPage();
    }
}
