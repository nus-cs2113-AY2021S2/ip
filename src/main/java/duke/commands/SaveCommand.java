package duke.commands;

import duke.tasks.TaskList;
import duke.ui.TextUI;
import duke.storage.Storage;
import static duke.common.Messages.DIVIDER;

/**
 * Command class for the save command. 
 */
public class SaveCommand extends Command {
    public static final String COMMAND_WORD = "save";
    private Storage storage;

    /**
     * Constructor for the <code>SaveCommand</code> class. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param storage storage instance to save the data
     */
    public SaveCommand(TaskList taskList, Storage storage) {
        super(taskList);
        this.storage = storage;
    }

    /**
     * Execute the save command. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param ui ui instance for the text to be displayed on
     */
    @Override
    public void execute(TaskList taskList, TextUI ui) {
        ui.printToScreen(DIVIDER);
        storage.saveData(ui);
        ui.printToScreen(DIVIDER);
    }
}
