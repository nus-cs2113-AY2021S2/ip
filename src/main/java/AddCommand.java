public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    public void execute(TaskList inputTasks, Storage storage, Ui ui) {
        assert task != null;
        inputTasks.addTask(this.task, ui);
        storage.writeToFile(inputTasks);
    }
}