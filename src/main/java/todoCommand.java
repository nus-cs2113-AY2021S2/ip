public class todoCommand extends Command{
    public static final String COMMAND_WORD = "todo";

    protected static improvedTask task;
    public static final String MESSAGE_SUCCESS = "Task: [%s] has been added.";


    public todoCommand(String task){
        this.task = new improvedTask(task, listTypes.todo);
    }

    @Override
    public CommandResult execute() {
            inputList.addTask(task);
            return new CommandResult(String.format(MESSAGE_SUCCESS, task.description));
    }


}
