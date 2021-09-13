/**
 * Contains methods for adding a deadline
 */
public class AddDeadlineCommand extends Command{

    private String messageToBeProcessed;

    /**
     * Constructs a command for adding a deadline
     * @param messageToBeProcessed user input
     */
    public AddDeadlineCommand(String messageToBeProcessed) {
        this.messageToBeProcessed = messageToBeProcessed;
    }

    /**
     * Call the addDeadline method
     *
     * @param tasks instantiation of ArrayList of Tasks
     * @param ui Ui instantiation
     * @param storage Storage instantiation
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addDeadline(tasks, ui, storage, messageToBeProcessed);
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
