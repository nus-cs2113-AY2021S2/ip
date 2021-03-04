package duke.commands;

import duke.data.task.Deadline;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * Adds a deadline item to the task list.
 */
public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Deadline to the task list. "
            + "Parameters: description, time\n"
            + "Example: " + COMMAND_WORD
            + " complete quiz by/ tonight";

    public static final String MESSAGE_SUCCESS = "Got it. I've added this task: %1$s\nNow you have %2$d tasks in the list.";

    private final Deadline toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws DukeException if any of the raw values are invalid
     */
    public AddDeadlineCommand(String description, String by) throws DukeException {
        if (description==null) {
            throw new DukeException("Description empty!");
        }
        if (by==null) {
            throw new DukeException("Time not specified!");
        }
        this.toAdd = new Deadline(description, by);
    }

    public AddDeadlineCommand(Deadline toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute() throws IOException {
        tasks.add(toAdd);
        int taskCount = tasks.size();
        storage.writeToFile(tasks);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd, taskCount));
    }
}
