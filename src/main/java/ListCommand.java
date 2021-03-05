public class ListCommand extends Command{

    public void execute(TaskList inputTasks, Storage storage, Ui ui){
        inputTasks.showTaskList(ui);
        storage.writeToFile(inputTasks);
    }
}