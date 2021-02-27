public class MarkAsDoneCommand extends Command{

    private int indexToBeProcessed;

    public MarkAsDoneCommand(int indexToBeProcessed) {
        this.indexToBeProcessed = indexToBeProcessed;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markDone(tasks, ui, storage, indexToBeProcessed);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
