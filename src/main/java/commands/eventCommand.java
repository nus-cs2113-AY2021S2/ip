package commands;

import duke.IncorrectFormatException;
import duke.improvedTask;
import duke.listTypes;

/**eventCommand class contains the command keyword for the event command and instructions to run when execute is called*/
public class eventCommand extends Command{
    public static final String COMMAND_WORD = "event";
    protected static improvedTask task;

    public static final String MESSAGE_SUCCESS = "Task: [%s] has been added.";

    public eventCommand(String desc, String date){
        this.task = new improvedTask(desc, listTypes.event, date);
    }

    @Override
    public CommandResult execute() throws IncorrectFormatException {
        if(task == null) {
            throw new IncorrectFormatException("Task was not entered!");
        }
        inputList.addTask(task);
        return new CommandResult(String.format(MESSAGE_SUCCESS, task.displayDescription()));
    }

}
