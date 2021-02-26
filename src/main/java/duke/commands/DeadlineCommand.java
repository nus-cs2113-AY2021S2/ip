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
     * Records a new Deadline task into the TaskList object.
     * Ensures the task description and deadline-by is in the commandArgs string.
     * Fails if any argument value is invalid and an error message is printed.
     *
     * @param tasks the TaskList object that contains the list of tasks.
     * @param ui the TextUI object that that engages user input and program output.
     * @param storage the Storage object that writes/retrieves to/from file.
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

    /**
     * Validates each argument for the task description and deadline-by.
     * Returns the extracted argument values in an array.
     * Throws an exception if there are missing argument values.
     *
     * @param commandArgs a string containing the command's arguments.
     * @return an array of size 2; first element is the task description
     *         and second element is the deadline-by string.
     * @throws DukeException If any of the arguments is empty.
     */
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
