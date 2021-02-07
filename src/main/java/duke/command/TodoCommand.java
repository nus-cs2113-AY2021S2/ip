package duke.command;

import duke.ui.Menu;
import duke.exception.MissingDescriptionException;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command {

    public TodoCommand(String commandArgs) {
        super(CommandType.TODO, commandArgs);
    }

    @Override
    public void execute(Task[] tasks) throws MissingDescriptionException {
        if (commandArgs.length() == 0) {
            throw new MissingDescriptionException(commandType);
        }
        Task task = new Todo(commandArgs);
        tasks[Task.getNumberOfTasks() - 1] = task;
        Menu.printAddedTask(task);
    }
}
