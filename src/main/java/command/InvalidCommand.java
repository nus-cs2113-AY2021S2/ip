package command;

/**
 * Represent an invalid command whose format is incorrect
 */
public class InvalidCommand extends Command{
    //private RuntimeException exception;
    public static final String ERROR_MESSGAE = "The command input is invalid.\n" + PRE_SPACE + VALID_COMMAND_LIST;
    private String feedbackFormat;

    public InvalidCommand(){}

    public InvalidCommand(RuntimeException e){
        feedbackFormat = e.toString();
    }

    public InvalidCommand(String errorMessage){
        feedbackFormat = errorMessage;
    }

    /**
     * @return the error message of the specified type of error
     */
    @Override
    public CommandResult execute(){
        return new CommandResult(feedbackFormat);
    }
}
