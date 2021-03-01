package duke.commands;

/**
 * Represents a command that display the help page containing all
 * valid commands and their formats.
 */
public class HelpCommand extends Command {

    @Override
    public void execute() {
        ui.printHelpPage();
    }
}
