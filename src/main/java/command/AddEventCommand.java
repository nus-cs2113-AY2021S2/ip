package command;


import task.Event;

/**
 * Represent a command of creating a new event task and adding it to the task list
 */
public class AddEventCommand extends Command{
    public static final String COMMAND_WORD = "event";
    public static final String ERROR_MESSAGE = "Name of event should be specified " +
            "after keyword event. Name and the time should be separated " +
            "by \"/at\"\n" + PRE_SPACE + "The syntax for adding an event task is:" +
            " <task name> /at <event time>    Eg. event meeting /at 8pm";
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
     */
    @Override
    public CommandResult execute() {
        tasks.addTask(newEvent);
        feedbackFormat = String.format(FEEDBACK_FORMAT, newEvent.getTaskInfo(), tasks.getTaskCount());
        return new CommandResult(feedbackFormat);
    }
}
