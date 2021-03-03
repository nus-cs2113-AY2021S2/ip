package command;

import exception.TaskFormatException;

/**
 * Represent an invalid command whose format is incorrect
 */
public class InvalidCommand extends Command{
    private RuntimeException exception;
    private String feedbackFormat;

    public InvalidCommand(){}

    public InvalidCommand(RuntimeException e){
        exception = e;
        feedbackFormat = e.toString();
    }

    /**
     * @return the error message of the specified type of error
     */
    @Override
    public CommandResult execute(){
        return new CommandResult(feedbackFormat);
    }
}
