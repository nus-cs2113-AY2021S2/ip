package duke.command;

import duke.tasklist.TaskList;

public class FindCommand extends Command{
    public FindCommand(String description) {
        TaskList.findListOfCommands(description);
    }
}
