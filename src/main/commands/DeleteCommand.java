package main.commands;

import main.Task;
import main.Ui;

/**
 * Deletes a task from the task list using its index in the task list
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by its index number used in the task list.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    private final int targetIndex;

    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex - 1;
    }

    @Override
    public CommandResult execute() {
        try {
            final Task taskToBeDeleted = taskList.get(targetIndex);
            taskList.deleteTask(targetIndex);
            return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToBeDeleted.toString()), taskList);
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Ui.MESSAGE_INVALID_TASK_INDEX, taskList);
        }
    }
}
