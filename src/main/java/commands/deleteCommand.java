package commands;

import duke.IncorrectFormatException;

/**deleteCommand class contains the command keyword for the Delete command and instructions to run when execute is called**/
public class deleteCommand extends Command{
    public static final String COMMAND_WORD = "delete";
    protected static String num;

    public static final String MESSAGE_SUCCESS = "Task has been deleted!";

    public deleteCommand(String number) throws IncorrectFormatException {
        if (number.isEmpty()){
            throw new IncorrectFormatException("Delete command format is incorrect!");
        }
        try{
            int temp = Integer.parseInt(number);
        }catch(NumberFormatException e){
            throw new IncorrectFormatException("Input is not a Number!");
        }
        this.num = number;

    }

    @Override
    public CommandResult execute() {
        inputList.deleteTask(this.num);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
