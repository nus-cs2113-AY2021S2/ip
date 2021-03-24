/**
 * mark a task as done
 */
public class DoneCommand extends Command{
    int taskIndex;

    /**
     * DoneCommand Constructor
     * @param taskIndex the index of task which is done
     */
    public DoneCommand(int taskIndex){
        super();
        this.taskIndex = taskIndex;
    }

    /**
     * execute of DoneCommand
     * @param inputTasks list of tasks
     * @param storage storage used
     * @param ui ui used
     */
    public void execute(TaskList inputTasks, Storage storage, Ui ui){
        assert taskIndex > 0;
        inputTasks.doneTask(taskIndex, ui);
        storage.writeToFile(inputTasks);
    }
}