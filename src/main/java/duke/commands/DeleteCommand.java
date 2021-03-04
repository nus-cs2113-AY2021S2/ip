package duke.commands;

import duke.data.task.Task;
import duke.exception.DukeException;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete the task identified by the index number used in the last listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Noted. I've removed this task: %1$s";

    public DeleteCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute() throws DukeException, IOException {
        ArrayList<Task> taskList = tasks.getTaskList();
        if (taskList.isEmpty()) {
            throw new DukeException("Empty list. Nothing to be excecuted.");
        }
        Task target = getTargetTask();
        tasks.remove(target);
        storage.writeToFile(tasks);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, target.getDescription()));
    }
}
