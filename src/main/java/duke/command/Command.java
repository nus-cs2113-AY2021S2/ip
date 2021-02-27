package duke.command;

import duke.DukeException;
import duke.DukeTaskList;

import java.util.ArrayList;

/**
 * The Command class represents a command to be executed in Duke.
 * Each instance of Command is constructed with its arguments, and the DukeTaskList for it to act on.
 * In addition, every subclass of Command implements a function, execute(), which causes it to execute its function.
 */
public abstract class Command {
    protected ArrayList<String> arguments;
    protected DukeTaskList dukeTaskList;

    /**
     * Create a new Command object with the specified arguments, as well as the DukeTaskList for it to act on.
     *
     * @param arguments ArrayList of Strings containing the arguments for the command
     * @param dukeTaskList the DukeTaskList object for it to act on
     */
    public Command(ArrayList<String> arguments, DukeTaskList dukeTaskList) {
        this.arguments = arguments;
        this.dukeTaskList = dukeTaskList;
    }

    /**
     * Executes the command with the arguments specified during construction.
     */
    public abstract void execute() throws DukeException;
}
