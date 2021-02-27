public class AddEventCommand extends Command{

    private String messageToBeProcessed;

    public AddEventCommand(String messageToBeProcessed) {
        this.messageToBeProcessed = messageToBeProcessed;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addEvent(tasks, ui, storage, messageToBeProcessed);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
