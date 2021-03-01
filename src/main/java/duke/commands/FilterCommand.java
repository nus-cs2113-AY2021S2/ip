package duke.commands;

import static duke.Ui.FILTERED_NO_RESULTS_MESSAGE;
import static duke.common.Constants.DEADLINE_TASK_TYPE;

import java.time.LocalDate;

import duke.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Task;

/**
 * Represents a command that searches for {@code Deadline} tasks in {@code TaskList} and
 * displays any that fall on a given deadline date, when executed.
 */
public class FilterCommand extends Command {
    private TaskList targetTasks;
    private LocalDate targetDate;

    public FilterCommand(TaskList tasks, LocalDate targetDate) {
        super.tasks = tasks;
        this.targetTasks = new TaskList();
        this.targetDate = targetDate;
    }

    @Override
    public void execute() {
        for (int i=0; i<tasks.getTasksCount(); i++) {
            Task task = tasks.getTaskAtIndex(i);
            if (task.getType().equals(DEADLINE_TASK_TYPE)) {
                checkTargetDate((Deadline) task);
            }
        }
        if (targetTasks.getTasksCount() < 1) {
            ui.printErrorMessage(FILTERED_NO_RESULTS_MESSAGE);
            return;
        }
        ui.printDeadlinesWithTargetDate(targetTasks);
    }

    /**
     * Checks if a {@code Deadline} is on a date matching the given {@code targetDate}.
     * @param possibleTarget {@code Deadline} task found in {@code TaskList}
     */
    public void checkTargetDate(Deadline possibleTarget) {
        LocalDate deadlineDate = possibleTarget.getDeadlineDate();
        if (deadlineDate.equals(targetDate)) {
            targetTasks.addTaskToList(possibleTarget);
        }
    }
}
