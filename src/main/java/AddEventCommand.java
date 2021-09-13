/**
 * Contains methods for adding a event
 */
public class AddEventCommand extends Command{

    private String messageToBeProcessed;

    /**
     * Constructs a command for adding an event
     * @param messageToBeProcessed user input
     */
    public AddEventCommand(String messageToBeProcessed) {
        this.messageToBeProcessed = messageToBeProcessed;
    }

    /**
     * Call the addEvent method
     *
     * @param tasks instantiation of ArrayList of Tasks
     * @param ui Ui instantiation
     * @param storage Storage instantiation
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addEvent(tasks, ui, storage, messageToBeProcessed);
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
