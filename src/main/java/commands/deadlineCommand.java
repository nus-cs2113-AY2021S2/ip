package commands;

import duke.IncorrectFormatException;
import duke.improvedTask;
import duke.listTypes;

public class deadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";
    protected static improvedTask task;

    public static final String MESSAGE_SUCCESS = "Task: [%s] has been added.";

    public deadlineCommand(String desc, String date) throws IncorrectFormatException {
        if (desc.isEmpty() || date.isEmpty()){
            throw new IncorrectFormatException("Deadline command format is incorrect!");
        }
        this.task = new improvedTask(desc, listTypes.deadline, date);
    }

    @Override
    public CommandResult execute() {
        inputList.addTask(task);
        return new CommandResult(String.format(MESSAGE_SUCCESS, task.displayDescription()));
    }





}
