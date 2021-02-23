package duke.commands;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;
import duke.exception.EmptyCommandArgException;
import duke.exception.InvalidTaskNumberException;

public class DeleteCommand extends Command {

    public DeleteCommand(String commandArg) {
        super(commandArg);
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Parser parser) throws EmptyCommandArgException, InvalidTaskNumberException {
        if (isEmptyArgument(commandArg)) {
            throw new EmptyCommandArgException("delete");
        }
        int taskNumber = Parser.getTaskNumber(commandArg, taskList);
        Task task = taskList.getTask(taskNumber - 1);
        taskList.deleteTask(taskNumber - 1);
        ui.printTaskSuccessfullyDeletedMessage(task);
    }
}