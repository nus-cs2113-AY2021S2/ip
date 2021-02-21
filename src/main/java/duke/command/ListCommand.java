package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.error.TaskListEmptyException;

public class ListCommand extends Command{
    public ListCommand(String[] commands) {
        super(commands);
    }


    /**
     * Lists all tasks current in the tasks list. Shows the type of task (T, D, E)
     * and marks X if the task is done.
     * 
     * @throws TaskListEmptyException If task list is empty.
     */
    protected static void executeListAllTasks(TaskList tasks, Ui ui) throws TaskListEmptyException {
        if (tasks.isTaskListEmpty()) {
            throw new TaskListEmptyException();
        }
        ui.displayToUser(tasks);
    }
}
