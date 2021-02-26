package duke.commands;

import duke.data.exceptions.DukeException;
import duke.data.task.Deadline;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

import static duke.commands.Utils.isArgumentValueEmpty;
import static duke.commands.Utils.parseArgument;
import static duke.common.Messages.ERROR_EMPTY_DEADLINE_BY_MESSAGE;
import static duke.common.Messages.ERROR_EMPTY_TASK_STRING_FORMAT;

public class DeadlineCommand extends Command {
    public static final String DEADLINE_WORD = "deadline";
    public static final String DEADLINE_BY_TOKEN = "/by";

    private String commandArgs;

    public DeadlineCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Records a new Deadline task into the global task array.
     * Ensures the task description and deadline-by is given in {@code commandArgs}
     * Fails if any argument value is invalid.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @see #validateDeadlineArguments(String)
     */
    private void recordDeadline(TaskList tasks, TextUI ui, Storage storage) {
        try {
            String[] deadlineArgValues = validateDeadlineArguments(commandArgs);
            tasks.recordTask(new Deadline(deadlineArgValues[0], deadlineArgValues[1]), ui, storage);
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        }
    }

    private String[] validateDeadlineArguments(String commandArgs) throws DukeException {
        String taskDescription = parseArgument(commandArgs, null, DEADLINE_BY_TOKEN);
        String deadlineBy = parseArgument(commandArgs, DEADLINE_BY_TOKEN, null);
        if (isArgumentValueEmpty(taskDescription)) {
            throw new DukeException(String.format(ERROR_EMPTY_TASK_STRING_FORMAT, "deadline"));
        }
        if (isArgumentValueEmpty(deadlineBy)) {
            throw new DukeException(ERROR_EMPTY_DEADLINE_BY_MESSAGE);
        }
        return new String[] {taskDescription, deadlineBy};
    }

    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        recordDeadline(tasks, ui, storage);
    }
}
