package command;

/**
 * Represent a command of deleting a task from the task list
 */
public class DeleteCommand extends Command{
    public static final String COMMAND_WORD = "delete";
    public static final String ERROR_MESSAGE = "Invalid input format for delete command.\n" + PRE_SPACE +
            "The syntax for deleting a task from current task list is: delete <task index>    Eg. delete 1";
    private String feedbackFormat;
    private int taskIndex;
    private final String FEEDBACK_FORMAT = "Noted! I've removed this task:\n" + PRE_SPACE + PRE_SPACE  +
            "%s\n" + PRE_SPACE + "Now you have %s tasks in the list.";

    public DeleteCommand(int taskIndex){
        this.taskIndex = taskIndex;
    }

    /**
     * delete the specified task from task list.
     * If a valid index is given, the task will be removed from the task list. A feedback message indicating
     * the successful removal of the task will be given.
     * If the given task index is invalid, like a negative number or a number out of range, an error message
     * will be returned.
     * @return feedback message of the command execution
     */
    @Override
    public CommandResult execute() {
        try{
            String taskInfo = tasks.get(taskIndex).getTaskInfo();
            tasks.deleteTask(taskIndex);
            feedbackFormat = String.format(FEEDBACK_FORMAT, taskInfo, tasks.getTaskCount());
            return new CommandResult(feedbackFormat);
        } catch (IndexOutOfBoundsException e){
            return new CommandResult("The task index is out of bound.");
        }
    }
}
