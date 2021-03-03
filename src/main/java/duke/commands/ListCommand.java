package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;
import static duke.common.Messages.DIVIDER;

/**
 * Command class for the list command. 
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Constructor for the <code>ListCommand</code> class. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     */
    public ListCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * Execute the list command. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param ui ui instance for the text to be displayed on
     */
    @Override
    public void execute(TaskList taskList, TextUI ui) {
        ui.printToScreen(DIVIDER);
        taskList.listTasks();
        ui.printToScreen(DIVIDER);
    }
}
