/**
 * shows the existing list of tasks recorded
 */
public class ListCommand extends Command{

    /**
     * execute ListCommand
     * @param inputTasks list of tasks
     * @param storage storage used
     * @param ui ui used
     */
    public void execute(TaskList inputTasks, Storage storage, Ui ui){
        inputTasks.showTaskList(ui);
        storage.writeToFile(inputTasks);
    }
}