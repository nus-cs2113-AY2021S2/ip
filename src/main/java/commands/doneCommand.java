package commands;

import duke.IncorrectFormatException;
import duke.todoList;

/**Command to set tasks as done based on listed number
 * */
public class doneCommand extends Command{
    public static final String COMMAND_WORD = "done";
    protected static String num;

    public static final String MESSAGE_SUCCESS = "Task has marked as done!";

    public doneCommand(String number) throws IncorrectFormatException {
        if (number.isEmpty()){
            throw new IncorrectFormatException("Done command format is incorrect!");
        }
        try{
            int temp = Integer.parseInt(number);
        }catch(NumberFormatException e){
            throw new IncorrectFormatException("Input is not a Number!");
        }
        num = number;
    }
    public doneCommand(todoList list){
        inputList = list;
        num = Integer.toString(inputList.tasksTotal());
    }

    @Override
    public CommandResult execute() {
        try {
            inputList.resolveTask(num);
            return new CommandResult(MESSAGE_SUCCESS);
        }catch (IndexOutOfBoundsException e){
            return new CommandResult("Selected task does not exist! Error: " + e.getMessage());
        }
    }

}
