package duke.commands;

import duke.TaskList;

/**
 * Interface for all Commands.
 * Abstract method ensures that all classes implementing Command can be executed.
 */
public interface Command {

    public void execute(TaskList tasks);

}
