package duke.command;

import java.io.IOException;

import duke.Constants;
import duke.TaskList;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(String[] commands) {
        super(commands);
        this.taskNumber = Integer.parseInt(commands[Constants.TASK_DESCRIPTION_INDEX]);
    }

    /**
     * Delete a task assigned to the task number. 
     * 
     * @param taskNumber The task number (starting from 1) that was marked as done. 
     */
    protected String executeDeleteTask(TaskList tasks) throws IOException{
        String deletedTask = tasks.getTask(taskNumber).toString();
        tasks.removeTask(taskNumber);
        return deletedTask;
    }
}
