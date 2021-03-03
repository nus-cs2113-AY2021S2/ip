package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;

/**
 * Command class for find function. 
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String searchWord;

    /**
     * Constructor for the <code>FindCommand</code> class.
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param searchWord keyword to be searched
     */
    public FindCommand(TaskList taskList, String searchWord) {
        super(taskList); 
        this.searchWord = searchWord;
    }

    /**
     * Execute the find command. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param ui ui instance for the text to be displayed on
     */
    @Override
    public void execute(TaskList taskList, TextUI ui) {
        taskList.findTask(searchWord);
    }
}
