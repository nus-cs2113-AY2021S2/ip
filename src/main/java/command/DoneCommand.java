package command;

import exception.DoneFormatException;
import exception.TaskAlreadyDoneException;
import task.ToDo;

/**
 *  Represent a command of setting a task as done
 */
public class DoneCommand extends Command{
    private String feedbackFormat;
    private int taskIndex;

    public DoneCommand(int taskIndex){
        this.taskIndex = taskIndex;
    }

    /**
     * Set the specified task as done
     * @return feedback message of command execution
     * @throws IndexOutOfBoundsException
     * @throws TaskAlreadyDoneException
     */
    @Override
    public CommandResult execute() throws IndexOutOfBoundsException, TaskAlreadyDoneException {
        try{
            tasks.setTaskDone(taskIndex);
            feedbackFormat = "Nice! I've marked this task as done:\n" + PRE_SPACE  + PRE_SPACE
                    + tasks.get(taskIndex).getTaskInfo();
            return new CommandResult(feedbackFormat);
        } catch (IndexOutOfBoundsException e){
            return new CommandResult("The task index is out of bound.");
        } catch (TaskAlreadyDoneException e){
            return new CommandResult(e.toString());
        }
    }
}
