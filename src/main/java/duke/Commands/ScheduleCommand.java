package duke.Commands;

import duke.Storage;
import duke.TaskList;
import duke.Tasks.Task;
import duke.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ScheduleCommand extends Command {
    private final LocalDate date;
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public ScheduleCommand(String dateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(dateTime, inputFormatter);
    }

    /**
     * Finds the <code>Task</code> objects that occurs on a specified <code>dateTime</code>
     * @param taskList The list of tasks stored in Duke
     * @param ui The <code>Ui</code> object that shows the output to the user
     * @param storage The <code>Storage</code> object that saves the updated tasks to the file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            if (task.getDateTime().format(outputFormatter).equals(date.format(outputFormatter))) {
                filteredTaskList.add(task);
            }
        }
        ui.showSchedule(filteredTaskList, date.format(outputFormatter));
    }
}
