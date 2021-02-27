package duke.commands;

import duke.ui.TextUI;
import duke.tasks.TaskList;

public class Command {
    public boolean isExit = false;
    public TaskList taskList;

    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    public void execute(TaskList taskList, TextUI ui) {
        ;
    }
}
