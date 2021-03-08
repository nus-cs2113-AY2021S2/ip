package main.commands;

import main.Task;
import main.Ui;

/**
 * Marks completed task as done using its index in the task list
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class MarkDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks completed task as done identified by its index number used in the task list.\n"
            + "Parameters: DONE\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_DONE_SUCCESS = "Completed Task: %1$s";

    private final int targetIndex;

    public MarkDoneCommand(int targetIndex) {
        this.targetIndex = targetIndex - 1;
    }

    @Override
    public CommandResult execute() {
        try {
            Task completedTask = taskList.get(targetIndex);
            completedTask.setDone(true);
            return new CommandResult(String.format(MESSAGE_MARK_TASK_DONE_SUCCESS, completedTask.toString()),
                    taskList);
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Ui.MESSAGE_INVALID_COMMAND, taskList);
        }
    }

}
