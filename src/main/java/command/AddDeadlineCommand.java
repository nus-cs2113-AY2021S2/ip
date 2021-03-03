package command;

import exception.DeadlineFormatException;
import exception.TaskFormatException;
import exception.TodoFormatException;
import task.Deadline;
import task.Task;
import task.TaskList;
import task.ToDo;

public class AddDeadlineCommand extends Command{
    private String feedbackFormat;
    private Deadline newDeadline;

    public AddDeadlineCommand(String name, String time){
        newDeadline = new Deadline(name, false, time);
    }

    @Override
    public CommandResult execute() throws TaskFormatException {
        tasks.addTask(newDeadline);
        feedbackFormat = "Deadline added:\n" + PRE_SPACE + PRE_SPACE + newDeadline.getTaskInfo() + "\n" +
                PRE_SPACE + "Now you have " + tasks.getTaskCount() + " tasks in the list";
        return new CommandResult(feedbackFormat);
    }
}
