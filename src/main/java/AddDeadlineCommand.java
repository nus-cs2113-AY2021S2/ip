public class AddDeadlineCommand extends Command{

    private String messageToBeProcessed;

    public AddDeadlineCommand(String messageToBeProcessed) {
        this.messageToBeProcessed = messageToBeProcessed;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addDeadline(tasks, ui, storage, messageToBeProcessed);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
