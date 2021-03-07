package Duke.Command;


import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

/**
 * Command can create commands to be executed.
 */
public abstract class Command {

    public abstract boolean shouldExit();

    /**
     * Executes the command based on the specific command type.
     *
     * @param tasks the list of tasks
     * @param ui do outputs
     * @param storage store the data
     * @param matchedTasks the list of matched tasks
     * @param sameDateTasks the list of tasks occurring on the same specified date
     * @throws DukeException the exceptions which can happen during command execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage, TaskList matchedTasks, TaskList sameDateTasks)
            throws DukeException;
}
