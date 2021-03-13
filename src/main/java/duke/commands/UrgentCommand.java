package duke.commands;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;

public class UrgentCommand extends Command {

    private TaskList urgentList;

    public UrgentCommand() {
        super();
        this.urgentList = new TaskList();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Parser parser) {
        for (int i = 0; i < taskList.getListSize(); i += 1) {
            Task task = taskList.getTask(i);
            if (task.isUrgent()) {
                urgentList.addTask(task);
            }
        }
        if (this.urgentList.getListSize() == 0) {
            ui.displayNoUrgentTasksMessage();
        } else {
            String listOfTasksString = ui.getListOfTasksString(this.urgentList);
            ui.displayUrgentTasks(listOfTasksString);
        }
    }
}
