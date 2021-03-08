package main.commands;


/**
 * Lists all tasks in the task list to the user.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public CommandResult execute() {
        return new CommandResult(taskList.taskListAsString(), taskList, COMMAND_WORD);
    }
}
