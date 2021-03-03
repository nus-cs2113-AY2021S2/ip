package duke.commands;

import duke.tasks.TaskList;
import duke.ui.TextUI;

/**
 * Command class for marking tasks as done. 
 */
public class FinishCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private final int taskIndex;

    /**
     * Constructor for the <code>FinishCommand</code> class. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param taskIndex index of the task to be marked as done
     */
    public FinishCommand(TaskList taskList, int taskIndex) {
        super(taskList);
        this.taskIndex = taskIndex;
    }

    /**
     * Execute the mark as done command. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param ui ui instance for the text to be displayed on
     */
    @Override
    public void execute(TaskList taskList, TextUI ui) {
        taskList.finishTask(taskIndex);
    }
}
