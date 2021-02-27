public class AddTodoCommand extends Command{

    private String messageToBeProcessed;

    public AddTodoCommand(String messageToBeProcessed) {
        this.messageToBeProcessed = messageToBeProcessed;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(tasks, ui, storage, messageToBeProcessed);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
