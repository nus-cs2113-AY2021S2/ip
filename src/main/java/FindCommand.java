/**
 * Contains methods for finding a task
 */
public class FindCommand extends Command{

    private String messageToBeProcessed;

    /**
     * Constructs a command for finding a task
     * @param messageToBeProcessed user input
     */
    public FindCommand(String messageToBeProcessed) {
        this.messageToBeProcessed = messageToBeProcessed;
    }

    /**
     * Calls the findTasks method
     *
     * @param tasks instantiation of ArrayList of Tasks
     * @param ui Ui instantiation
     * @param storage Storage instantiation
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTasks(messageToBeProcessed);
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
