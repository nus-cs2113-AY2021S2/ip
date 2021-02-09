package duke.command;

import duke.main.DukeException;

public interface Command {
    public void execute(String string) throws DukeException;
}
