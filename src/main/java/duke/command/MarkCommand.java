package duke.command;

import duke.Constants;
import duke.TaskList;
import duke.task.Task;

public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(String[] commands) {
        super(commands);
        this.taskNumber = Integer.parseInt(commands[Constants.TASK_DESCRIPTION_INDEX]);
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Marks a given task number as done.
     * 
     * @param taskNumber The task number (starting from 1) that was marked as done.
     */
    protected void executeMarkTask(TaskList tasks) {
        tasks.getTask(taskNumber).setTaskStatus();
    }

    /**
     * Marks a given task number as done.
     * 
     * @param taskNumber The task number (starting from 1) that was marked as done.
     */
    protected void executeMarkTask(Task task) {
        task.setTaskStatus();
    }
}
