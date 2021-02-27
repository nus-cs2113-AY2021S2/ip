package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;
import duke.storage.Storage;
import static duke.common.Messages.DIVIDER;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public ExitCommand(TaskList taskList, TextUI ui, Storage storage) {
        super(taskList);
        ui.printToScreen(DIVIDER);
        if (storage.saveData(ui)) {
            ui.showExitMessage();
            this.isExit = true;
        } else {
            ui.printToScreen("Failed to exit.");
        }
        ui.printToScreen(DIVIDER);
    }
}