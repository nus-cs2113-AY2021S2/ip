package duke.command;

import duke.tasklist.TaskList;

public class DeleteCommand extends Command{
    /**
     * Delete task input by user
     *
     * @param description input by user
     */
    public DeleteCommand(String description) {
        TaskList.deleteTask(description);
    }
}
