package duke.command;

import duke.tasklist.TaskList;

/**
 * Marks a task as done
 *
 * @param description input by user
 */

public class MarkAsDoneCommand extends Command{
    public MarkAsDoneCommand(String description) {
        TaskList.markAsDone(description);
    }
}
