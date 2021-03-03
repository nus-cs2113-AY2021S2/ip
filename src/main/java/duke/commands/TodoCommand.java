package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * Command class for todos. 
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private final String description;

    /**
     * Construtor for the <code>TodoCommand</code> class. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param description description of the todo
     */
    public TodoCommand(TaskList taskList, String description) {
        super(taskList);
        this.description = description;
    }

    /**
     * Execute the todo command. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param ui ui instance for the text to be displayed on
     */
    @Override
    public void execute(TaskList taskList, TextUI ui) {
        taskList.addTask(new Todo(description));
    }
}
