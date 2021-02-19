package commands;

import duke.improvedTask;
import duke.listTypes;

public class eventCommand extends Command{
    public static final String COMMAND_WORD = "event";
    protected static improvedTask task;

    public static final String MESSAGE_SUCCESS = "Task: [%s] has been added.";

    public eventCommand(String desc, String date){
        this.task = new improvedTask(desc, listTypes.event, date);
    }

    @Override
    public CommandResult execute() {
        inputList.addTask(task);
        return new CommandResult(String.format(MESSAGE_SUCCESS, task.displayDescription()));
    }

}
