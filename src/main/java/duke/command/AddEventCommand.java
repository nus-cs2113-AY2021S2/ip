package duke.command;

import duke.tasklist.TaskList;

public class AddEventCommand extends Command {
    /**
     * Add event task to TaskList
     *
     * @param description input by user
     */
    public AddEventCommand(String description) {
        TaskList.addEvent(description);
    }
}
