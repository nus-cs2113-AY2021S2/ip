package commands;

import duke.IncorrectFormatException;
import duke.ImprovedTask;
import duke.ListTypes;

/**
 * deadlineCommand class contains the command keyword for the deadline command and instructions to run when execute is called
 **/
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    protected static ImprovedTask task;

    public static final String MESSAGE_SUCCESS = "Task: [%s] has been added.";

    public DeadlineCommand(String desc, String date) throws IncorrectFormatException {
        if (desc.isEmpty() || date.isEmpty()) {
            throw new IncorrectFormatException("Deadline command format is incorrect!");
        }
        task = new ImprovedTask(desc, ListTypes.deadline, date);
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
