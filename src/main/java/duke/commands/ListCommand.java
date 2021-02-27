package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;
import static duke.common.Messages.DIVIDER;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand(TaskList taskList) {
        super(taskList);
    }

    public void execute(TaskList taskList, TextUI ui) {
        ui.printToScreen(DIVIDER);
        taskList.listTasks();
        ui.printToScreen(DIVIDER);
    }
}
