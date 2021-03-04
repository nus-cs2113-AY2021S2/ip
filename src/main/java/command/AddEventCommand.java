package command;

import exception.DeadlineFormatException;
import exception.EventFormatException;
import exception.TaskFormatException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;

import javax.swing.*;

/**
 * Represent a command of creating a new event task and adding it to the task list
 */
public class AddEventCommand extends Command{
    private Event newEvent;
    private String feedbackFormat;
    private final String FEEDBACK_FORMAT = "Event added:\n" + PRE_SPACE + PRE_SPACE + "%s\n" +
            PRE_SPACE + "Now you have %s tasks in the list";

    public AddEventCommand(String name, String time){
        newEvent = new Event(name, false, time);
    }

    /**
     * add the new event to task list
     * @return the feedback message of execution
     * @throws TaskFormatException
     */
    @Override
    public CommandResult execute() throws TaskFormatException {
        tasks.addTask(newEvent);
        feedbackFormat = String.format(FEEDBACK_FORMAT, newEvent.getTaskInfo(), tasks.getTaskCount());
        return new CommandResult(feedbackFormat);
    }
}
