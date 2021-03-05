package command;


import exception.TaskAlreadyDoneException;

/**
 *  Represent a command of setting a task as done
 */
public class DoneCommand extends Command{
    public static final String COMMAND_WORD = "done";
    public static final String ERROR_MESSAGE = "Invalid input format for done command.\n" + PRE_SPACE +
            "The syntax for marking a task as done is: done <task index>    Eg. done 1";
    //private String feedback;
    private int taskIndex;
    private final String FEEDBACK_FORMAT = "Nice! I've marked this task as done:\n" + PRE_SPACE + PRE_SPACE + "%s";

    public DoneCommand(int taskIndex){
        this.taskIndex = taskIndex;
    }

    /**
     * Set the specified task as done
     * If a valid index is given, the task will be marked as done. A feedback message indicatinG the successful
     * removal of the task will be given. If the given task index is invalid, like a negative number or a number
     * out of range, an error message will be returned. If the given task is already set as done, an error message
     * will be returned.
     * @return feedback message of command execution
     */
    @Override
    public CommandResult execute() {
        try{
            tasks.setTaskDone(taskIndex);
            feedback = String.format(FEEDBACK_FORMAT, tasks.get(taskIndex).getTaskInfoForDisplay());
            return new CommandResult(feedback);
        } catch (IndexOutOfBoundsException e){
            return new CommandResult("The task index is out of bound.");
        } catch (TaskAlreadyDoneException e){
            return new CommandResult(e.toString());
        }
    }
}
