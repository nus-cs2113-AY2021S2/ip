/**
 * deletes a task from task list
 */
public class DeleteCommand extends Command{
    int taskIndex;

    /**
     * DeleteCommand Constructor
     * @param taskIndex index of task to be deleted
     */
    public DeleteCommand(int taskIndex){
        super();
        this.taskIndex = taskIndex;
    }

    /**
     * execute of DeleteCommand
     * @param inputTasks list of tasks
     * @param storage storage used
     * @param ui ui used
     */
    public void execute(TaskList inputTasks, Storage storage, Ui ui){
        assert taskIndex > 0;
        inputTasks.deleteTask(taskIndex, ui);
        storage.writeToFile(inputTasks);
    }

}