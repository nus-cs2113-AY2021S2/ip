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
     * Records a new Todo task into the TaskList object.
     * Ensures the task description is in the commandArgs string.
     * Fails if task description argument value is invalid.
     *
     * @param tasks the TaskList object that contains the list of tasks.
     * @param ui the TextUI object that that engages user input and program output.
     * @param storage the Storage object that writes/retrieves to/from file.
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

    /**
     * Validates the argument for the task description.
     * Returns the extracted argument value as a string.
     * Throws an exception if the task description argument is missing.
     *
     * @param commandArgs a string containing the command's argument.
     * @return the task description
     * @throws DukeException If any of the arguments is empty.
     */
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
