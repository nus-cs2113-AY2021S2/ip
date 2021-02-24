package duke.command;

public class ListCommand extends Command {
    public static final String LIST_COMMAND = "list";

    @Override
    public CommandResult execute() {
        return new CommandResult(ListMessage());
    }

    private String ListMessage() {
        StringBuilder listMessage = new StringBuilder("Here are the task in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            int indexShownToUser = i+1;
            listMessage.append(indexShownToUser).append(".").append(tasks.get(i)).append("\n");
        }
        return listMessage.toString();
    }
}
