package duke.command;

import java.io.IOException;

import duke.Constants;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents the mark command. An MarkCommand object corresponds to the mark command input by the user. 
 */
public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(String[] commands) {
        super(commands);
        this.taskNumber = Integer.parseInt(commands[Constants.TASK_DESCRIPTION_INDEX]);
    }

    /**
     * Get task number.
     * 
     * @return Task number. 
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Marks a given task number as done. 
     * Displays the task taht has been marked and save changes to file. 
     * Used for retrieving based on user input. 
     * 
     * @param taskNumber The task number (starting from 1) that was marked as done.
     */
    protected void executeMarkTask(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.getTask(taskNumber).setTaskStatus();
        ui.displayMarkTaskSuccessMessage(tasks, taskNumber);
        storage.saveToFile(tasks);
    }
}
