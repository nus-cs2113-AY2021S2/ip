package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

/**
 * DeleteCommand is called when user wants to delete a task.
 */
public class DeleteCommand extends Command {
    private boolean isExit;
    private String fullCommand;
    private static final int DELETE_LENGTH = 7;

    /**
     * Constructor method of DeleteCommand which stores the user input.
     *
     * @param fullCommand String of user input for deleting a task.
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Deletes a task from the TaskList tasks.
     * Prints output confirming task deletion to user.
     * Saves changes to data file.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui User Interface.
     * @param storage Storage to save all tasks, updating the data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(fullCommand.substring(DELETE_LENGTH)) - 1;
            Task deleted = tasks.getTask(index);
            tasks.removeTask(index);
            ui.printRemoveTask(deleted, tasks.getTaskCount());
            storage.saveToFile(tasks);
        } catch (Exception e) {
            ui.printInvalidTask();
        }
    }
}
