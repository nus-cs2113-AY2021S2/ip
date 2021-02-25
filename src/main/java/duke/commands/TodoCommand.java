package duke.commands;

import duke.data.exceptions.DukeException;
import duke.data.task.TaskList;
import duke.data.task.Todo;
import duke.storage.Storage;
import duke.ui.TextUI;

import static duke.commands.Utils.isArgumentValueEmpty;
import static duke.commands.Utils.parseArgument;
import static duke.common.Messages.ERROR_EMPTY_TASK_STRING_FORMAT;

public class TodoCommand extends Command {
    public static final String TODO_WORD = "todo";

    private String commandArgs;

    public TodoCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Records a new Todo task into the global task array.
     * Ensures the task description is given in {@code commandArgs}
     * Fails if task description argument value is invalid.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @see #validateTodoArguments(String)
     */
    private void recordTodo(TaskList tasks, TextUI ui, Storage storage) {
        try {
            String taskDescription = validateTodoArguments(commandArgs);
            tasks.recordTask(new Todo(taskDescription), ui, storage);
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        }
    }

    private String validateTodoArguments(String commandArgs) throws DukeException {
        String taskDescription = parseArgument(commandArgs, null);
        if (isArgumentValueEmpty(taskDescription)) {
            throw new DukeException(String.format(ERROR_EMPTY_TASK_STRING_FORMAT, "todo"));
        }
        return taskDescription;
    }

    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        recordTodo(tasks, ui, storage);
    }
}
