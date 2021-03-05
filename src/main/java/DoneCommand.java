public class DoneCommand extends Command{
    int taskIndex;

    public DoneCommand(int taskIndex){
        super();
        this.taskIndex = taskIndex;
    }


    public void execute(TaskList inputTasks, Storage storage, Ui ui){
        assert taskIndex > 0;
        inputTasks.doneTask(taskIndex, ui);
        storage.writeToFile(inputTasks);
    }
}