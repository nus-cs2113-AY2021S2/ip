package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;

public class HelpCmd extends Command {
    public HelpCmd(String s) {
        super(s);
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
