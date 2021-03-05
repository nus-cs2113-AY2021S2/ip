package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;

/**
 * Base class of a command. 
 */
public class Command {
    public boolean isExit = false;
    public TaskList taskList;

    /**
     * Constructor for the <code>Command</code> class. 
     * 
     * @param taskList taskList instance on which the command is to be executed
     */
    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Execute the command. This is overloaded in the specific commands. 
     * @param taskList tasklist instance on which the command is to be executed
     * @param ui ui instance for the text to be displayed on
     */
    public void execute(TaskList taskList, TextUI ui) {
        ;
    }
}
