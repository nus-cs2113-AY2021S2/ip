/**
 * abstract command class
 */
public abstract class Command {
    /**
     * execute of command class
     * @param inputTasks list of tasks
     * @param storage storage used
     * @param ui ui used
     * @throws DukeException exceptions used
     */
    public abstract void execute(TaskList inputTasks, Storage storage, Ui ui) throws DukeException;
}