
public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    public void execute(TaskList inputTasks, Storage storage, Ui ui) {
        System.out.println(ui.showGoodbye());
        System.exit(0);
    }
}