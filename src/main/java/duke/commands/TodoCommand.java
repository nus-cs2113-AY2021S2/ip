package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;
import duke.tasks.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private final String description;

    public TodoCommand(TaskList taskList, String description) {
        super(taskList);
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, TextUI ui) {
        taskList.addTask(new Todo(description));
    }
}
