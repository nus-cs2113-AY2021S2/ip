package duke.command;

import duke.exception.InvalidTaskNumberException;
import duke.exception.MissingDescriptionException;
import duke.parser.Parser;
import duke.task.Task;
import duke.ui.Menu;

import java.util.ArrayList;

public class DeleteCommand extends Command {

    public DeleteCommand(String commandArgs) {
        super(CommandType.DELETE, commandArgs);
    }

    @Override
    public void execute(ArrayList<Task> tasks) throws MissingDescriptionException, InvalidTaskNumberException {
        if (commandArgs.length() == 0) {
            throw new MissingDescriptionException(commandType);
        }
        if (!Parser.isValidTaskNumber(tasks, commandArgs)) {
            throw new InvalidTaskNumberException(commandArgs);
        }
        int taskIndex = Integer.parseInt(commandArgs) - 1;
        Task taskRemoved = tasks.remove(taskIndex);
        Task.saveAllTasks(tasks);
        Menu.printDeleteTask(taskRemoved);
    }
}
