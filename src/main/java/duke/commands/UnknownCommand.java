package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;

/**
 * Command class for unknown commands. 
 */
public class UnknownCommand extends Command {

    /**
     * Constructor for the <code>UnknownCommand</code> class. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     */
    public UnknownCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * Execute the find command. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param ui ui instance for the text to be displayed on
     */
    @Override
    public void execute(TaskList taskList, TextUI ui) {
        ui.showUnknownMessage();
    }
}
