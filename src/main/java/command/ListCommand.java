package command;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";
    private static final String FEEDBACK_FORMAT = "Here are the tasks in your list:";

    @Override
    public CommandResult execute(){
        return new CommandResult(FEEDBACK_FORMAT, tasks);
    }
}
