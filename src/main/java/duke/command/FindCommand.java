package duke.command;

import duke.tasklist.TaskList;

public class FindCommand extends Command{
    /**
     * Find list of tasks with input by user
     *
     * @param description input by user
     */
    public FindCommand(String description) {
        TaskList.findListOfCommands(description);
    }
}
