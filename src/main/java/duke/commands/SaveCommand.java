package duke.commands;

import duke.tasks.TaskList;
import duke.ui.TextUI;
import duke.storage.Storage;
import static duke.common.Messages.DIVIDER;

public class SaveCommand extends Command {
    public static final String COMMAND_WORD = "save";
    private Storage storage;

    public SaveCommand(TaskList taskList, Storage storage) {
        super(taskList);
        this.storage = storage;
    }

    @Override
    public void execute(TaskList taskList, TextUI ui) {
        ui.printToScreen(DIVIDER);
        storage.saveData(ui);
        ui.printToScreen(DIVIDER);
    }
}
