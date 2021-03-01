package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public int taskIndex;

    public DeleteCommand(TaskList taskList, int taskIndex) {
        super(taskList);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, TextUI ui) {
        taskList.deleteTask(taskIndex);
    }
}