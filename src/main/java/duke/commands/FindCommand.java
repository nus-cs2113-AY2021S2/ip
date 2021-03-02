package duke.commands;

import static duke.Ui.FOUND_NO_RESULTS_MESSAGE;
import static duke.Ui.PRINT_TARGET_LIST_STATEMENT;

import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents a command that searches for tasks in the main {@code TaskList}
 * containing a given keyword and displays any in a new {@code TaskList}, when executed.
 */
public class FindCommand extends Command {
    private final TaskList targetTasks;
    private final String targetKeyword;

    public FindCommand(TaskList tasks, String keyword) {
        super.tasks = tasks;
        super.ui = new Ui();
        targetTasks = new TaskList();
        targetKeyword = keyword.toLowerCase();
    }

    @Override
    public void execute() {
        for (int i=0; i<tasks.getTasksCount(); i++) {
            Task task = tasks.getTaskAtIndex(i);
            if (task.getItem().toLowerCase().contains(targetKeyword)) {
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
