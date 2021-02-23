package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.MissingDescriptionException;
import duke.task.Task;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String commandArgs) {
        super(CommandType.DEADLINE, commandArgs);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (commandArgs.length() == 0) {
            throw new MissingDescriptionException(commandType);
        }
        String[] deadlineArgs = commandArgs.split("\\s+/by\\s+", 2);
        Task task = new Deadline(deadlineArgs[0], deadlineArgs[1]);
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
