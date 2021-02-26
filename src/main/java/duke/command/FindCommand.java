package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.exception.UnmatchListException;
import duke.task.Task;

/**
 * Represent a command to find the items in the TaskList
 * with the user input word.
 */
public class FindCommand extends Command{
    public static final String FIND_COMMAND = "find";
    public static final String EMPTY_STRING = "";
    private static final int COUNTER_START_VALUE = 1;
    private boolean isMatch;

    public FindCommand (String description) throws EmptyDescriptionException {
        this.description = description;
        this.isMatch = false;
        if (description.equals(EMPTY_STRING)) {
            throw new EmptyDescriptionException();
        }
    }

    @Override
    public CommandResult execute() {
        try {
            return new CommandResult(findMessage());
        } catch (UnmatchListException unmatchListException) {
            return new InvalidCommand(commandWord, description, unmatchListException).execute();
        }
    }

    private boolean isFound(int taskIndex, String description) {
        Task task = tasks.get(taskIndex);
        String taskDescription = task.getDescription();
        return taskDescription.contains(description);
    }

    private void setIsMatch () {
        isMatch = true;
    }

    private boolean getIsMatch() {
        return isMatch;
    }

    private String findMessage() throws UnmatchListException {
        StringBuilder find = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = COUNTER_START_VALUE;
        for (int i = 0; i < tasks.size(); ++i) {
            if (isFound(i,description)) {
                find.append(count).append(".").append(tasks.get(i)).append("\n");
                ++count;
                setIsMatch();
            }
        }

        if (getIsMatch() == false) {
            throw new UnmatchListException();
        }

        return find.toString();
    }
}

