package duke.command;

import duke.Constants;
import duke.TaskList;
import duke.Ui;
import duke.error.TaskListEmptyException;

public class FindCommand extends Command {
    private String searchString;

    public FindCommand(String[] commands) {
        super(commands);
        this.searchString = commands[Constants.TASK_DESCRIPTION_INDEX];
    }

    protected void executeFindTask(TaskList tasks, Ui ui) throws TaskListEmptyException {
        if (tasks.isTaskListEmpty()) {
            throw new TaskListEmptyException();
        }
        ui.displayToUser(tasks, searchString);
    }
}
