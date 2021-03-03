package command;

import exception.TaskAlreadyDoneException;

/**
 * Represent a command of deleting a task from the task list
 */
public class DeleteCommand extends Command{
    private String feedbackFormat;
    private int taskIndex;

    public DeleteCommand(int taskIndex){
        this.taskIndex = taskIndex;
    }

    /**
     * delete the specified task from task list.
     * @return feedback message of the command execution
     * @throws IndexOutOfBoundsException
     * @throws TaskAlreadyDoneException
     */
    @Override
    public CommandResult execute() throws IndexOutOfBoundsException, TaskAlreadyDoneException {
        try{
            String taskInfo = tasks.get(taskIndex).getTaskInfo();
            tasks.deleteTask(taskIndex);
            feedbackFormat = "Noted! I've removed this task:\n" + PRE_SPACE + PRE_SPACE + taskInfo +
                    "\n" + PRE_SPACE + "Now you have " + tasks.getTaskCount() + " tasks in the list.";
            return new CommandResult(feedbackFormat);
        } catch (IndexOutOfBoundsException e){
            return new CommandResult("The task index is out of bound.");
        }
    }
}
