package duke.commands;

import duke.data.task.Todo;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * Adds a to-do item to the task list.
 */
public class AddTodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Todo to the task list. "
            + "Parameters: description\n"
            + "Example: " + COMMAND_WORD
            + " do a project increment";

    public static final String MESSAGE_SUCCESS = "Got it. I've added this task: %1$s\nNow you have %2$d tasks in the list.";

    private final Todo toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws DukeException if any of the raw values are invalid
     */
    public AddTodoCommand(String description) throws DukeException {
        if (description==null) {
            throw new DukeException("Description empty!");
        }
        this.toAdd = new Todo(description);
    }

    public AddTodoCommand(Todo toAdd) {
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
