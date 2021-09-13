/**
 * Contains methods for deleting a task
 */
public class DeleteTaskCommand extends Command{

    private int indexToBeProcessed;

    /**
     * Constructs a command for deleting a task
     * @param indexToBeProcessed user input
     */
    public DeleteTaskCommand(int indexToBeProcessed) {
        this.indexToBeProcessed = indexToBeProcessed;
    }

    /**
     * Calls the deleteTask method
     *
     * @param tasks instantiation of ArrayList of Tasks
     * @param ui Ui instantiation
     * @param storage Storage instantiation
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(tasks, ui, storage, indexToBeProcessed);
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
