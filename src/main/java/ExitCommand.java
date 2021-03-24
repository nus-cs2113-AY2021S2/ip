/**
 * Exit the program
 */
public class ExitCommand extends Command {

    /**
     * ExitCommand Constructor
     */
    public ExitCommand() {
        super();
    }

    /**
     * execute of ExitCommand
     * @param inputTasks list of tasks
     * @param storage storage used
     * @param ui ui used
     */
    public void execute(TaskList inputTasks, Storage storage, Ui ui) {
        System.out.println(ui.showGoodbye());
        System.exit(0);
    }
}