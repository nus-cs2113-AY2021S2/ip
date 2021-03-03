package Duke.Commands;

import Duke.Output;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class DateCommand extends Command {

    LocalDate queryDate;

    public DateCommand(LocalDate queryDate) {
        this.queryDate = queryDate;
    }
    public String execute(TaskList taskList, Storage storage) {
        ArrayList<Task> filteredTasks = taskList.findByDate(queryDate);
        return Output.printTaskList(filteredTasks);
    }
}
