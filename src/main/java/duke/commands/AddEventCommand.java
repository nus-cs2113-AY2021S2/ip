package duke.commands;

import duke.data.task.Event;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * Adds an event item to the task list.
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the task list. "
            + "Parameters: description, venue\n"
            + "Example: " + COMMAND_WORD
            + " participate in audition at/ music room";

    public static final String MESSAGE_SUCCESS = "Got it. I've added this task: %1$s\nNow you have %2$d tasks in the list.";

    private final Event toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws DukeException if any of the raw values are invalid
     */
    public AddEventCommand(String description, String at) throws DukeException {
        if (description==null) {
            throw new DukeException("Description empty!");
        }
        if (at==null) {
            throw new DukeException("Time not specified!");
        }
        this.toAdd = new Event(description, at);
    }

    public AddEventCommand(Event toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute() throws IOException {
        tasks.add(toAdd);
        storage.writeToFile(tasks);
        int taskCount = tasks.size();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd, taskCount));
    }
}
