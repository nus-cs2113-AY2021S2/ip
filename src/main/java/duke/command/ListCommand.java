package duke.command;

import duke.common.Messages;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to list all tasks
 */
public class ListCommand extends Command {
    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printText(Messages.INFO_LIST_TASKS);
        tasks.getTasks().stream()
                .forEach((t) -> ui.printText(" " + tasks.getTaskNumber(t) + ". " + t));
    }

    /**
     * @return boolean result if Duke should exit after execution of command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
