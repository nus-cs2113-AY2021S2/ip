package command;

public class HelpCommand extends Command{
    public static final String COMMAND_WORD = "help";
    private static final String PRE_SPACE = "    ";
    private static final String FEEDBACK_FORMAT = "Duke accepts the following command:\n" +
            "    1.list\n      List current task list\n    2.todo <task name>\n" +
            "      Add a new todo task to task list\n      Eg. todo do homework\n" +
            "    3.deadline <task name> /by <deadline>\n" +
            "      Add a new deadline to task list\n      Eg. deadline return a book /by tonight\n" +
            "    4.event <task name> /at <time>\n      Add a new event to task list\n" +
            "      Eg. event meeting /at 8pm\n    5.done <task index>\n      Mark a task as done\n" +
            "      Eg. done 1\n    6.delete <task index>\n      Delete a task from task list\n" +
            "      Eg. delete 2\n    7.find <keyword>\n      Search task list for tasks whose names contain the keyword\n" +
            "      Eg. find book\n    8.help\n      Show help message";

    @Override
    public CommandResult execute(){
        return new CommandResult(FEEDBACK_FORMAT);
    }
}
