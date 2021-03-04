/**
 * Contains method for showing help message
 */
public class ShowHelpCommand extends Command{

    /**
     * Constructs a blank instantiation of command that does not take inputs
     */
    public ShowHelpCommand() {
    }

    /**
     * Calls the showHelp method
     *
     * @param tasks instantiation of ArrayList of Tasks
     * @param ui Ui instantiation
     * @param storage Storage instantiation
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }

    /**
     * Command does not exit the loop
     *
     * @return whether to exit the loop
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
