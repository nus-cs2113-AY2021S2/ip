public class doneCommand extends Command{
    public static final String COMMAND_WORD = "done";
    protected static String num;

    public static final String MESSAGE_SUCCESS = "Task has marked as done!";

    public doneCommand(String number){
        this.num = number;
    }

    @Override
    public CommandResult execute() {
        inputList.resolveTask(this.num);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
