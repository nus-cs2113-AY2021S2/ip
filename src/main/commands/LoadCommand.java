package main.commands;

import main.Storage;
import main.Ui;

/**
 * Loads the saved task list data to the current runtime.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class LoadCommand extends Command {
    public static final String COMMAND_WORD = "load";

    public static final String MESSAGE_LOAD_TASK_LIST_SUCCESS = "Task List loaded from memory successfully!";

    @Override
    public CommandResult execute() {
        try {
            taskList = Storage.loadData();
            return new CommandResult(MESSAGE_LOAD_TASK_LIST_SUCCESS, taskList);
        } catch (Storage.StorageOperationException e) {
            e.printStackTrace();
            return new CommandResult(Ui.MESSAGE_INVALID_COMMAND, taskList);
        }
    }
}