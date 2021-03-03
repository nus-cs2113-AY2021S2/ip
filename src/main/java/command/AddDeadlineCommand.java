package command;


import exception.TaskFormatException;
import exception.TodoFormatException;
import task.Deadline;
import task.Task;
import task.TaskList;
import task.ToDo;

/**
 * Represent a command of creating a new deadline task and adding it to the task list
 */

public class AddDeadlineCommand extends Command{
    private String feedbackFormat;
    private Deadline newDeadline;

    public AddDeadlineCommand(String name, String time){
        newDeadline = new Deadline(name, false, time);
    }

    /**
     * add the new deadline to task list
     * @return the feedback message of execution
     * @throws TaskFormatException
     */
    @Override
    public CommandResult execute() throws TaskFormatException {
        tasks.addTask(newDeadline);
        feedbackFormat = "Deadline added:\n" + PRE_SPACE + PRE_SPACE + newDeadline.getTaskInfo() + "\n" +
                PRE_SPACE + "Now you have " + tasks.getTaskCount() + " tasks in the list";
        return new CommandResult(feedbackFormat);
    }
}
