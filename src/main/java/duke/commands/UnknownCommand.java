package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;

public class UnknownCommand extends Command {

    public UnknownCommand(TaskList taskList) {
        super(taskList);
    }

    @Override
    public void execute(TaskList taskList, TextUI ui) {
        ui.showUnknownMessage();
    }
}
