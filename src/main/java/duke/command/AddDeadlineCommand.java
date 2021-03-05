package duke.command;

import duke.tasklist.TaskList;

public class AddDeadlineCommand extends Command {

    /**
     * Add deadline task to TaskList
     *
     * @param description input by user
     */
    public AddDeadlineCommand(String description) {
        TaskList.addDeadline(description);
    }
}
