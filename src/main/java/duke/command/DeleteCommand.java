package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private boolean isExit;
    private String fullCommand;
    private static final int DELETE_LENGTH = 7;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(fullCommand.substring(DELETE_LENGTH)) - 1;
            Task deleted = tasks.getTask(index);
            tasks.removeTask(index);
            ui.printRemoveTask(deleted, tasks.getTaskCount());
            storage.saveToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTask();
        }
    }
}
