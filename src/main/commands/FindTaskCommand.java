package main.commands;

import main.TaskList;
import main.Ui;

/**
 * Find a task from the task list using user specified keyword
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class FindTaskCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Find tasks in the task list that matches given keywords.\n"
            + "Parameters: FIND\n"
            + "Example: " + COMMAND_WORD + " book";

    public static String MESSAGE_FIND_TASK_SUCCESS = "Task that contains: ";

    public String keywords;

    public FindTaskCommand(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute() {
        try {
            TaskList matchedTaskList = taskList.findMatchedTask(keywords);
            return new CommandResult(MESSAGE_FIND_TASK_SUCCESS, COMMAND_WORD,
                    taskList, matchedTaskList);
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Ui.MESSAGE_INVALID_COMMAND, taskList);
        }
    }
}
