package duke.command;

/**
 * Represent a command to delete the Task in TaskList.
 */
public class DeleteCommand extends Command {
    private final int indexFromList;

    private static final int LIST_INITIAL_INDEX = 1;
    public static final String DELETE_COMMAND = "delete";

    /**
     * Construct DeleteCommand instance.
     *
     * @param taskNumber the position of Task to be deleted in TaskList.
     */
    public DeleteCommand(int taskNumber) {
        super();
        this.indexFromList = taskNumber - LIST_INITIAL_INDEX;
        this.commandWord = DELETE_COMMAND;
        this.description = Integer.toString(taskNumber);
    }

    @Override
    public CommandResult execute() {
        try {
            CommandResult commandResult = new CommandResult(deleteMessage());
            tasks.deleteTask(indexFromList);
            return commandResult;
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return new InvalidCommand(commandWord, description, indexOutOfBoundsException).execute();
        }
    }

    private String deleteMessage() {
        int listSizeAfterDelete = tasks.size() - 1;
        String deleteMessage = "Noted! I've removed this task:\n"
                + "  " + tasks.get(indexFromList) + "\n"
                + "Now you have " + listSizeAfterDelete
                + " tasks in the list\n";
        return deleteMessage;
    }
}
