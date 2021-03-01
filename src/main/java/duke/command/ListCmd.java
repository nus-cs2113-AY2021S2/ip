package duke.command;

import duke.Storage;
import duke.Ui;
import duke.TaskManager;

public class ListCmd extends Command{
    public ListCmd(String s) {
        super(s);
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) {
        tasks.listAllTasks();
    }
}
