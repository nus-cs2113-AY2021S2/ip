package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command{

    protected int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            TaskList.deleteTask(taskIndex);
        } catch (DukeException e) {
            ui.showInvalidTaskIndex(taskIndex);
        }
    }
}
