package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DefaultCommand extends Command {

    protected String input;

    public DefaultCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.mockEcho(input);
        ui.showHint();
    }
}
