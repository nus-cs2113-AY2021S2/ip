package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {

    private String line;

    public DoneCommand(String line) {
        this.line = line;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markDone(line);
        storage.saveFile(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
