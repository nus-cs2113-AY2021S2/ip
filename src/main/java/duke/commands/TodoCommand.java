package duke.commands;

import duke.task.TaskList;
import duke.task.Todo;
import duke.util.Parser;
import duke.util.Ui;
import duke.exception.EmptyCommandArgException;

public class TodoCommand extends Command {

    public TodoCommand(String commandArg) {
        super(commandArg);
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Parser parser) throws EmptyCommandArgException {
        if (isEmptyArgument(commandArg)) {
            throw new EmptyCommandArgException("todo");
        }
        Todo task = new Todo(commandArg);
        taskList.addTask(task);
        ui.printTaskSuccessfullyAddedMessage(task, taskList.getListSize());
    }
}