package duke.command;

import duke.common.Messages;
import duke.common.Utils;
import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

/**
 * Command to mark task as done
 */
public class DoneCommand extends Command {
    /**
     * Construct for DoneCommand. Takes in command arguments, sets the command type and arguments.
     * @param commandArgs command arguments from user input
     */
    public DoneCommand(String commandArgs) {
        super(CommandType.DONE, commandArgs);
    }

    /**
     * Handles marking the tasks as done by their task number, saving and printing output information.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!Utils.isValidTaskNumber(tasks.getTasks(), commandArgs)) {
            throw new InvalidTaskNumberException(commandArgs);
        }
        int taskIndex = Integer.parseInt(commandArgs) - 1;
        Task task = tasks.getTaskByIndex(taskIndex);
        if (task.isDone()) {
            ui.printText(Messages.INFO_TASK_ALREADY_MARKED);
            return;
        }
        task.setDone(true);
        storage.save(tasks.getTasks());
        ui.printText(Messages.INFO_TASK_MARKED + task);
    }

    /**
     * @return boolean result if Duke should exit after execution of command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
