package duke.commands;

import duke.TaskList;

/**
 * Displays all the tasks currently in the tasklist.
 */
public class ListCommand implements Command {

    public ListCommand() {};

    public void execute(TaskList tasks) {
        tasks.listTasks();
    }
}
