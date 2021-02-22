package duke.Command;

import duke.*;
import duke.task.Task;

public class AddCommand extends Command {

    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.addTask(this.task);
        } catch (DukeException e) {
            throw new DukeException();
        } catch (Exception e) {
            ui.showError(e);
        }
    }
}
