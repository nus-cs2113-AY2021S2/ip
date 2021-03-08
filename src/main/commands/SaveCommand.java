package main.commands;

import main.Storage;
import main.Ui;

/**
 * Saves current task list data to storage.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class SaveCommand extends Command {

    public static final String COMMAND_WORD = "save";

    public static final String MESSAGE_SAVE_TASK_LIST_SUCCESS = "Task List saved successfully!";

    @Override
    public CommandResult execute() {
        try {
            Storage.save(taskList);
            return new CommandResult(MESSAGE_SAVE_TASK_LIST_SUCCESS, taskList);
        } catch (Storage.StorageOperationException e) {
            return new CommandResult(Ui.MESSAGE_INVALID_COMMAND, taskList);
        }
    }
}
