public class DeleteCommand extends Command{
    int taskIndex;

    public DeleteCommand(int taskIndex){
        super();
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList inputTasks, Storage storage, Ui ui){
        assert taskIndex > 0;
        inputTasks.deleteTask(taskIndex, ui);
        storage.writeToFile(inputTasks);
    }

}