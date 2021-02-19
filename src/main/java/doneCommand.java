public class doneCommand extends Command{
    public static final String COMMAND_WORD = "done";
    protected static String num;

    public static final String MESSAGE_SUCCESS = "Task has marked as done!";

    public doneCommand(String number) throws IncorrectFormatException {
        if (number.isEmpty()){
            throw new IncorrectFormatException("Delete command format is incorrect!");
        }
        try{
            int temp = Integer.parseInt(number);
        }catch(NumberFormatException e){
            throw new IncorrectFormatException("Input is not a Number!");
        }
        num = number;
    }
    public doneCommand(){
        num = Integer.toString(inputList.tasksTotal());
    }

    @Override
    public CommandResult execute() {
        inputList.resolveTask(num);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
