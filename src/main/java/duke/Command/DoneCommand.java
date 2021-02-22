package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {

    protected int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            TaskList.markIndexDone(taskIndex);
        } catch (DukeException e) {
            ui.showInvalidTaskIndex(taskIndex);
        }
    }
}
