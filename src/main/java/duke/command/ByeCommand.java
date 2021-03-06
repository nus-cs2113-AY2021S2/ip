package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to terminate Duke
 */
public class ByeCommand extends Command {
    /**
     * Constructor for ByeCommand. Sets the command type.
     */
    public ByeCommand() {
        super(CommandType.BYE);
    }

    /**
     * Executes printing of bye message.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printBye();
    }

    /**
     * @return boolean result if Duke should exit after execution of command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
