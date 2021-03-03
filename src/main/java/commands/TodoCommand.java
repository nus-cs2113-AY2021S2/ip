package commands;

import duke.IncorrectFormatException;
import duke.ImprovedTask;
import duke.ListTypes;


/**
 * todoCommand class contains the command keyword for the deadline command and instructions to run when execute is called
 **/
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    protected static ImprovedTask task;
    public static final String MESSAGE_SUCCESS = "Task: [%s] has been added.";


    public TodoCommand(String task) throws IncorrectFormatException {
        if (task.equals("")) {
            throw new IncorrectFormatException("No task was entered!");
        }
        this.task = new ImprovedTask(task, ListTypes.todo);
    }

    @Override
    public CommandResult execute() throws IncorrectFormatException {
        if (task.displayDescription() == null) {
            throw new IncorrectFormatException("Task was not entered!");
        }
        inputList.addTask(task);
        return new CommandResult(String.format(MESSAGE_SUCCESS, task.displayDescription()));
    }


}
