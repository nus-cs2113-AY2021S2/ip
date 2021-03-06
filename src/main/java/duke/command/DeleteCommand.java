package duke.command;

import duke.common.Utils;
import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberException;
import duke.exception.MissingDescriptionException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to delete tasks
 */
public class DeleteCommand extends Command {
    /**
     * Constructor for DeleteCommand. Takes in command arguments, sets command type and arguments.
     * @param commandArgs command arguments from user input
     */
    public DeleteCommand(String commandArgs) {
        super(CommandType.DELETE, commandArgs);
    }

    /**
     * Handles deleting tasks, saving and printing output information.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (commandArgs.length() == 0) {
            throw new MissingDescriptionException(commandType);
        }
        if (!Utils.isValidTaskNumber(tasks.getTasks(), commandArgs)) {
            throw new InvalidTaskNumberException(commandArgs);
        }
        int taskIndex = Integer.parseInt(commandArgs) - 1;
        Task taskRemoved = tasks.deleteTaskByIndex(taskIndex);
        storage.save(tasks.getTasks());
        ui.printDeletedTask(taskRemoved);
    }

    /**
     * @return boolean result if Duke should exit after execution of command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
