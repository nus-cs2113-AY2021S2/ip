package duke.command;

import duke.Constants;
import duke.TaskList;
import duke.Ui;
import duke.error.TaskListEmptyException;

/**
 * Represents the find command. An FindCommand object corresponds to the find command input by the user. 
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String[] commands) {
        super(commands);
        this.keyword = commands[Constants.TASK_DESCRIPTION_INDEX];
    }

    /**
     * Finds and displays all tasks that has a description that matches the keyword. 
     * 
     * @throws TaskListEmpty If task list is empty. 
     */
    protected void executeFindTask(TaskList tasks, Ui ui) throws TaskListEmptyException {
        if (tasks.isTaskListEmpty()) {
            throw new TaskListEmptyException();
        }
        ui.displayToUser(tasks, keyword);
    }
}
