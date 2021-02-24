package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.task.Task;

public class FindCommand extends Command{
    public static final String FIND_COMMAND = "find";
    public static final String EMPTY_STRING = "";
    private static final int COUNTER_START_VALUE = 1;

    public FindCommand (String description) throws EmptyDescriptionException {
        this.description = description;
        if (description.equals(EMPTY_STRING)) {
            throw new EmptyDescriptionException();
        }
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(findMessage());
    }

    private boolean isFound(int taskIndex, String description) {
        Task task = tasks.get(taskIndex);
        String taskDescription = task.getDescription();
        return taskDescription.contains(description);
    }

    private String findMessage() {
        StringBuilder find = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = COUNTER_START_VALUE;
        for (int i = 0; i < tasks.size(); ++i) {
            if (isFound(i,description)) {
                find.append(count).append(".").append(tasks.get(i)).append("\n");
                ++count;
            }
        }
        return find.toString();
    }
}

