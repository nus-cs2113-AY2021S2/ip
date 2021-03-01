package duke.commands;

import duke.tasks.TaskList;
import duke.ui.TextUI;

public class FinishCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private final int taskIndex;

    public FinishCommand(TaskList taskList, int taskIndex) {
        super(taskList);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, TextUI ui) {
        taskList.finishTask(taskIndex);
    }
}
