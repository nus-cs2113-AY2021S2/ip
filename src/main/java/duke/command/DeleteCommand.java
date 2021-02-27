package duke.command;

import java.io.IOException;
import duke.Constants;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the delete command. An DeleteCommand object corresponds to the delete command input by the user. 
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(String[] commands) {
        super(commands);
        this.taskNumber = Integer.parseInt(commands[Constants.TASK_DESCRIPTION_INDEX].trim());
    }

    /**
     * Delete a task assigned to the task number. 
     * Save to storage and display success message.
     * Applies to user input commands. 
     * 
     * @param taskNumber The task number (starting from 1) that was marked as done. 
     */
    protected void executeDeleteTask(TaskList tasks, Ui ui, Storage storage) throws IOException{
        String deletedTask = tasks.getTask(taskNumber).toString();
        tasks.removeTask(taskNumber);
        storage.saveToFile(tasks);
        ui.displayDeleteTaskSuccessMessage(deletedTask);
    }
}
