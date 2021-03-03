package commands;

import duke.IncorrectFormatException;
import duke.ImprovedTask;
import duke.ListTypes;

/**
 * eventCommand class contains the command keyword for the event command and instructions to run when execute is called
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    protected static ImprovedTask task;

    public static final String MESSAGE_SUCCESS = "Task: [%s] has been added.";

    public EventCommand(String desc, String date) throws IncorrectFormatException {
        if (desc.isEmpty() || date.isEmpty()) {
            throw new IncorrectFormatException("Event command format is incorrect!");
        }
        this.task = new ImprovedTask(desc, ListTypes.event, date);
    }

    @Override
    public CommandResult execute() throws IncorrectFormatException {
        if (task == null) {
            throw new IncorrectFormatException("Task was not entered!");
        }
        inputList.addTask(task);
        return new CommandResult(String.format(MESSAGE_SUCCESS, task.displayDescription()));
    }

}
