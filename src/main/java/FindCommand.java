public class FindCommand extends Command{

    private String messageToBeProcessed;

    public FindCommand(String messageToBeProcessed) {
        this.messageToBeProcessed = messageToBeProcessed;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTasks(messageToBeProcessed);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
