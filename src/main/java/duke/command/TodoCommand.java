package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.MissingDescriptionException;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command {
    public TodoCommand(String commandArgs) {
        super(CommandType.TODO, commandArgs);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (commandArgs.length() == 0) {
            throw new MissingDescriptionException(commandType);
        }
        Task task = new Todo(commandArgs);
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        ui.printAddedTask(task);
        ui.printTotalTasks(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
