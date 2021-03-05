package command;

import task.Deadline;

/**
 * Represent a command of creating a new deadline task and adding it to the task list
 */

public class AddDeadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";
    public static final String ERROR_MESSAGE = "Name of deadline should be specified " +
            "after keyword deadline. Name and the time to finish should be separated " +
            "by \"/by\"\n" + PRE_SPACE + "The syntax for adding a deadline task is:" +
            " <task name> /by <deadline>    Eg. deadline return a book /by tonight";
    private String feedbackFormat;
    private Deadline newDeadline;
    private final String FEEDBACK_FORMAT = "Deadline added:\n" + PRE_SPACE + PRE_SPACE + "%s\n" +
            PRE_SPACE + "Now you have %s tasks in the list";

    public AddDeadlineCommand(String name, String time){
        newDeadline = new Deadline(name, false, time);
    }

    /**
     * add the new deadline to task list
     * @return the feedback message of execution
     */
    @Override
    public CommandResult execute() {
        tasks.addTask(newDeadline);
        feedbackFormat = String.format(FEEDBACK_FORMAT, newDeadline.getTaskInfo(), tasks.getTaskCount());
        return new CommandResult(feedbackFormat);
    }
}
