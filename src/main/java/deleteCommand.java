public class deleteCommand extends Command{
    public static final String COMMAND_WORD = "delete";
    protected static String num;

    public static final String MESSAGE_SUCCESS = "Task has been deleted!";

    public deleteCommand(String number){
        this.num = number;
    }

    @Override
    public CommandResult execute() {
        inputList.deleteTask(this.num);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
