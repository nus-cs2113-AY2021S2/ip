package duke.command;

import duke.tasklist.TaskList;

public class AddTodoCommand extends Command {
    /**
     * Add todo task to TaskList
     *
     * @param description input by user
     */
    public AddTodoCommand(String description) {
        TaskList.addToDo(description);
    }
}
