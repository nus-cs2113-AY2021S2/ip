package duke.command;

import duke.common.Messages;
import duke.common.Utils;
import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class DoneCommand extends Command {
    public DoneCommand(String commandArgs) {
        super(CommandType.DONE, commandArgs);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!Utils.isValidTaskNumber(tasks.getTasks(), commandArgs)) {
            throw new InvalidTaskNumberException(commandArgs);
        }
        int taskIndex = Integer.parseInt(commandArgs) - 1;
        Task task = tasks.getTaskByIndex(taskIndex);
        if (task.isDone()) {
            ui.printText(Messages.MESSAGE_TASK_ALREADY_MARKED);
            return;
        }
        task.setDone(true);
        storage.save(tasks.getTasks());
        ui.printText(Messages.MESSAGE_TASK_MARKED + task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
