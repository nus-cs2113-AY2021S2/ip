package duke.commands;

import duke.data.task.Task;
import duke.exception.DukeException;

import java.util.ArrayList;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark the task complete, identified by the index number used in the last listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "Nice! I've marked this task as done: %1$s";

    public DoneCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute() throws DukeException {
        ArrayList<Task> taskList = tasks.getTaskList();
        if (taskList.isEmpty()) {
            throw new DukeException("Empty list. Nothing to be excecuted.");
        }
        Task target = getTargetTask();
        if (target.getIsDone()){
            throw new DukeException("Task has already been marked done.");
        }
        target.markAsDone();
        return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS, target.getDescription()));
    }
}
