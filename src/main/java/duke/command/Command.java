package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Parent class for more specific command child class.
 * Contains basic information applicable to all command-related classes.
 */
public abstract class Command {
    /**
     * Executes command based on the corresponding command type.
     *
     * @param taskList Task list consisting of all tasks.
     * @param ui User interface for printing result.
     * @param storage Storage for storing task list data.
     * @throws DukeException If something goes wrong when executing command.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a flag indicating whether the program should terminate after execution of current command.
     *
     * @return True if the program should be terminated after executing current command, False otherwise.
     */
    public abstract boolean isExit();
}
