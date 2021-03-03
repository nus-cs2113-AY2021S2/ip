package duke.commands;

import duke.TaskList;

public class ListCommand implements Command {

    public ListCommand() {};

    public void execute(TaskList tasks) {
        tasks.listTasks();
    }
}
