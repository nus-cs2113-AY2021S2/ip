public class DeleteTaskCommand extends Command{

    private int indexToBeProcessed;

    public DeleteTaskCommand(int indexToBeProcessed) {
        this.indexToBeProcessed = indexToBeProcessed;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(tasks, ui, storage, indexToBeProcessed);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
