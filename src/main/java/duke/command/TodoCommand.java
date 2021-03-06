package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.MissingDescriptionException;
import duke.task.Task;
import duke.task.Todo;

/**
 * Command to add a todo task
 */
public class TodoCommand extends Command {
    /**
     * Constructor for TodoCommand. Takes in command arguments, sets command type and arguments.
     * @param commandArgs
     */
    public TodoCommand(String commandArgs) {
        super(CommandType.TODO, commandArgs);
    }

    /**
     * Handles adding todo tasks, saving and printing output information.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
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

    /**
     * @return boolean result if Duke should exit after execution of command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
