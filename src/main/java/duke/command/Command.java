package duke.command;

import duke.main.DukeException;

/**
 * Represents a Command Class
 */
public interface Command {

    /**
     * Execute the command
     *
     * @param string
     * @throws DukeException
     */
    public void execute(String string) throws DukeException;
}
