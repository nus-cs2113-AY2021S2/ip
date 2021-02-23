package duke.commands;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Ui;
import duke.exception.EmptyCommandArgException;
import duke.exception.InvalidTaskNumberException;

public class DoneCommand extends Command {

    public DoneCommand(String commandArg) {
        super(commandArg);
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyCommandArgException, InvalidTaskNumberException {
        if (isEmptyArgument(commandArg)) {
            throw new EmptyCommandArgException("done");
        }
        int taskNumber = getTaskNumber(commandArg, taskList);
        Task task = taskList.getTask(taskNumber - 1);
        task.setIsDone();
        ui.printSuccessfullyMarkedDoneMessage(task);
    }
}