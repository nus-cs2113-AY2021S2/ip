/**
 * Contains methods for marking tasks as done
 */
public class MarkAsDoneCommand extends Command{

    private int indexToBeProcessed;

    /**
     * Constructs a command for marking a task as done
     * @param indexToBeProcessed user input
     */
    public MarkAsDoneCommand(int indexToBeProcessed) {
        this.indexToBeProcessed = indexToBeProcessed;
    }

    /**
     * Calls the markDone method
     *
     * @param tasks instantiation of ArrayList of Tasks
     * @param ui Ui instantiation
     * @param storage Storage instantiation
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markDone(tasks, ui, storage, indexToBeProcessed);
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
