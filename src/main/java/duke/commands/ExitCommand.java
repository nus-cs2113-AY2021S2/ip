package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;
import duke.storage.Storage;
import static duke.common.Messages.DIVIDER;

/**
 * Command class for the exiting. 
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    /**
     * Constructor for the <code>ExitCommand</code> class. This is executed immediately without a execute method. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param ui ui instance for the text to be displayed on
     * @param storage storage instance for the handling of data storage before exiting
     */
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