package duke.commands;

import static duke.Ui.FOUND_NO_RESULTS_MESSAGE;
import static duke.Ui.PRINT_TARGET_LIST_STATEMENT;

import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class FindCommand extends Command {
    private TaskList targetTasks;
    private String targetKeyword;

    public FindCommand(TaskList tasks, String keyword) {
        super.tasks = tasks;
        super.ui = new Ui();
        targetTasks = new TaskList();
        targetKeyword = keyword;
    }

    @Override
    public void execute() {
        for (int i=0; i<tasks.getTasksCount(); i++) {
            Task task = tasks.getTaskAtIndex(i);
            if (task.getItem().contains(targetKeyword)) {
                targetTasks.addTaskToList(task);
            }
        }
        if (targetTasks.getTasksCount() < 1) {
            ui.printErrorMessage(FOUND_NO_RESULTS_MESSAGE);
            return;
        }
        ui.printList(targetTasks, PRINT_TARGET_LIST_STATEMENT);
    }
}
