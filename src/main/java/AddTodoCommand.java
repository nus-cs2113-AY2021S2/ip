/**
 * Contains methods for adding a task
 */
public class AddTodoCommand extends Command{

    private String messageToBeProcessed;

    /**
     * Constructs a command for adding a task
     * @param messageToBeProcessed user input
     */
    public AddTodoCommand(String messageToBeProcessed) {
        this.messageToBeProcessed = messageToBeProcessed;
    }

    /**
     * Call the addTasks method
     *
     * @param tasks instantiation of ArrayList of Tasks
     * @param ui Ui instantiation
     * @param storage Storage instantiation
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(tasks, ui, storage, messageToBeProcessed);
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
