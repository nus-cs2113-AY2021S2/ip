/**
 * Contains methods for changing the exit status
 */
public class ByeCommand extends Command{

    /**
     * Constructs a blank instantiation of command that does not take inputs
     */
    public ByeCommand() {
    }

    /**
     * Blank initiation of a command class
     *
     * @param tasks instantiation of ArrayList of Tasks
     * @param ui Ui instantiation
     * @param storage Storage instantiation
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    /**
     * Command exits the loop
     *
     * @return whether to exit the loop
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
