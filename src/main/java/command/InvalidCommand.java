package command;

/**
 * Represents an invalid command whose format is invalid
 */
public class InvalidCommand extends Command{
    public static final String ERROR_MESSGAE = "The command input is invalid.\n" + PRE_SPACE + VALID_COMMAND_LIST;
    //private String feedback;

    public InvalidCommand(){
        feedback = ERROR_MESSGAE;
    }

    public InvalidCommand(String errorMessage){
        feedback = errorMessage;
    }

    @Override
    public CommandResult execute(){
        return new CommandResult(feedback);
    }
}
