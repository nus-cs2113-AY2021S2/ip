package commands;

import duke.IncorrectFormatException;
import duke.ImprovedTask;

import java.util.ArrayList;

/**
 * Command to find task entries based on a keyword and instructions to run when execute is called*
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    protected static String keyword;
    protected static String MESSAGE_SUCCESS = "";

    public FindCommand(String input) throws IncorrectFormatException {
        if (input.isEmpty()) {
            throw new IncorrectFormatException("Find command format is incorrect!");
        }
        keyword = input;
    }

    @Override
    public CommandResult execute() throws IncorrectFormatException {
        MESSAGE_SUCCESS = "";
        ArrayList<ImprovedTask> foundTasks = inputList.findTasksWithKeyword(keyword);
        if (foundTasks.size() == 0) {
            MESSAGE_SUCCESS = "No Tasks matching keyword were found!";
        } else {
            MESSAGE_SUCCESS += "The following task(s) were found: \n";
            for (int i = 0; i < foundTasks.size(); i++) {
                if (i < foundTasks.size() - 1) {
                    MESSAGE_SUCCESS += Integer.toString(i + 1)
                            + ". " + foundTasks.get(i).displayType()
                            + " " + foundTasks.get(i).displayResolved()
                            + " " + foundTasks.get(i).displayDescription()
                            + " " + foundTasks.get(i).displayDate() + "\n";
                } else {
                    MESSAGE_SUCCESS += Integer.toString(i + 1)
                            + ". " + foundTasks.get(i).displayType()
                            + " " + foundTasks.get(i).displayResolved()
                            + " " + foundTasks.get(i).displayDescription()
                            + " " + foundTasks.get(i).displayDate();
                }
            }
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
