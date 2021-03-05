package command;

/**
 * Represents a command of removing all tasks in current task list
 */
public class ClearCommand extends Command{
    public static final String COMMAND_WORD = "clear";
    private int taskIndex;
    private final String FEEDBACK_FORMAT = "Noted! I've removed all the tasks in current task list.";

    public ClearCommand(){
        feedback = FEEDBACK_FORMAT;
    }

    /**
     * Clears current task list.
     *
     * @return feedback message of the command execution
     */
    @Override
    public CommandResult execute() {
        tasks.clearTasks();
        return new CommandResult(feedback);
    }
}
