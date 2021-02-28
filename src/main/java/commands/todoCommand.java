package commands;

import duke.IncorrectFormatException;
import duke.improvedTask;
import duke.listTypes;
import duke.task;

public class todoCommand extends Command{
    public static final String COMMAND_WORD = "todo";

    protected static improvedTask task;
    public static final String MESSAGE_SUCCESS = "Task: [%s] has been added.";


    public todoCommand(String task){
        this.task = new improvedTask(task, listTypes.todo);
    }

    @Override
    public CommandResult execute() throws IncorrectFormatException {
        if(task.displayDescription() == null) {
            throw new IncorrectFormatException("Task was not entered!");
        }
        inputList.addTask(task);
        return new CommandResult(String.format(MESSAGE_SUCCESS, task.displayDescription()));
    }


}
