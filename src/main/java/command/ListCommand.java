package command;

/**
 * Represents a command of listing all items in task list
 */
public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";
    private static final String FEEDBACK_FORMAT = "Here are the tasks in your list:";

    public ListCommand(){
        feedback = FEEDBACK_FORMAT;
    }

    @Override
    public CommandResult execute(){
        return new CommandResult(feedback, tasks);
    }
}
