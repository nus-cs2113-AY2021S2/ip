package duke.command;

public class DoneCommand extends Command {
    private final int indexFromList;

    private static final int LIST_INITIAL_INDEX = 1;
    public static final String DONE_COMMAND = "done";

    public DoneCommand(int taskNumber) {
        super();
        this.indexFromList = taskNumber - LIST_INITIAL_INDEX;
        this.commandWord = DONE_COMMAND;
        this.description = Integer.toString(taskNumber);
    }

    @Override
    public CommandResult execute() {
        try {
            tasks.get(indexFromList).setAsDone();
            return new CommandResult(doneMessage());
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return new InvalidCommand(commandWord, description, indexOutOfBoundsException).execute();
        }
    }

    private String doneMessage () {
        String doneMessage = "Nice! I've marked this as done: \n"
                + "  " + tasks.get(indexFromList).toString() + "\n";
        return doneMessage;
    }
}
