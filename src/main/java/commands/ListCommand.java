package commands;

/**
 * Command to list all current tasks
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private static String MESSAGE_SUCCESS = "Task: [%s] has been added.";


    @Override
    public CommandResult execute() {

        MESSAGE_SUCCESS = inputList.listItems();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
