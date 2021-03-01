package duke.command;

import duke.Storage;
import duke.Ui;
import duke.TaskManager;

public class ExitCmd extends Command{
    public ExitCmd(String s) {
      super(s);
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) {
        ui.showBye();
        System.exit(0);
    }
}
