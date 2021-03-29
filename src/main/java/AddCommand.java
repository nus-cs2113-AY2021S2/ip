/**
 * adds task following user command
 */
public class AddCommand extends Command {
    Task task;

    /**
     * AddCommand Constructor
     * @param task task to be added
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * execute of command
     * @param inputTasks list of tasks
     * @param storage storage used
     * @param ui ui used
     */
    public void execute(TaskList inputTasks, Storage storage, Ui ui) {
        assert task != null;
        inputTasks.addTask(this.task, ui);
        storage.writeToFile(inputTasks);
    }
}