package duke.command;

import duke.DukeException;
import duke.DukeTaskList;

import java.util.ArrayList;

public abstract class Command {
    protected ArrayList<String> arguments;
    protected DukeTaskList dukeTaskList;

    public Command(ArrayList<String> arguments, DukeTaskList dukeTaskList) {
        this.arguments = arguments;
        this.dukeTaskList = dukeTaskList;
    }

    public abstract void execute() throws DukeException;
}
