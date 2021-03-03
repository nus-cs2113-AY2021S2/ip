package duke.commands;

import duke.TaskList;

public interface Command {

    public void execute(TaskList tasks);

}
