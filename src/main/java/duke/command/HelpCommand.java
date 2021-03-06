package duke.command;

import duke.common.Messages;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to list usage information
 */
public class HelpCommand extends Command {
    /**
     * Constructor for HelpCommand. Sets the command type.
     */
    public HelpCommand() {
        super(CommandType.HELP);
    }

    /**
     * Handles printing usage information.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printText(Messages.INFO_HELP);
    }

    /**
     * @return boolean result if Duke should exit after execution of command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
