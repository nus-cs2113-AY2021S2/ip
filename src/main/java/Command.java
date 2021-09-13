/**
 * Contains basic information for a command structure
 */
public abstract class Command {

    /**
     * Constructs an empty instantiation of this class
     */
    public Command() {
    }

    /**
     * Format for constructing a command
     *
     * @param tasks instantiation of ArrayList of Tasks
     * @param ui Ui instantiation
     * @param storage Storage instantiation
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Format for method to exiting the loop
     *
     * @return whether to exit the loop
     */
    public abstract boolean isExit();
}
