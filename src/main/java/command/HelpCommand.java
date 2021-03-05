package command;

/**
 * Represents the command of displaying help message
 */
public class HelpCommand extends Command{
    public static final String COMMAND_WORD = "help";
    private static final String PRE_SPACE = "    ";
    private static final String FEEDBACK_FORMAT = VALID_COMMAND_LIST;

    public HelpCommand(){
        feedback = FEEDBACK_FORMAT;
    }

    @Override
    public CommandResult execute(){
        return new CommandResult(feedback);
    }
}
