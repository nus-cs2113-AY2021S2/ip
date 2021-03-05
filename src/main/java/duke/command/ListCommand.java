package duke.command;

import duke.tasklist.TaskList;

public class ListCommand extends Command{
    /**
     * Print list of existing tasks
     */
    public ListCommand() {
        TaskList.showList();
    }
}
