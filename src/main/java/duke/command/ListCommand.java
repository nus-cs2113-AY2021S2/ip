package duke.command;

import duke.tasks.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;


public class ListCommand extends Command{
    public ListCommand() {
        TaskList.showList();
    }
}
