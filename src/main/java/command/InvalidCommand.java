package command;

import exception.TaskFormatException;

public class InvalidCommand extends Command{
    private RuntimeException exception;
    private String feedbackFormat;

    public InvalidCommand(){}

    public InvalidCommand(RuntimeException e){
        exception = e;
        feedbackFormat = e.toString();
    }

    @Override
    public CommandResult execute(){
        return new CommandResult(feedbackFormat);
    }
}
