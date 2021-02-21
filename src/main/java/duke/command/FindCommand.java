package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String line;

    public FindCommand(String line) {
        this.line = line;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTask(line);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
