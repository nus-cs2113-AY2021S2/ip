package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;

/**
 * Command class for the delete function
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public int taskIndex;

    /**
     * Constructor for the <code>DeleteCommand</code> class. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param taskIndex index of the task to be deleted
     */
    public DeleteCommand(TaskList taskList, int taskIndex) {
        super(taskList);
        this.taskIndex = taskIndex;
    }

    /**
     * Execute the delete command
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param ui ui instance for the text to be displayed on
     */
    @Override
    public void execute(TaskList taskList, TextUI ui) {
        taskList.deleteTask(taskIndex);
    }
}