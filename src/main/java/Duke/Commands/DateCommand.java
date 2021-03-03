package Duke.Commands;

import Duke.Output;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class DateCommand extends Command {

    /**
     * LocalDate object to be used for querying by the user
     */
    LocalDate queryDate;

    /**
     * Constructor
     * @param queryDate
     */
    public DateCommand(LocalDate queryDate) {
        this.queryDate = queryDate;
    }

    /**
     * Execute the command
     * @param taskList
     * @param storage
     * @return String to be printed to the command line
     */
    public String execute(TaskList taskList, Storage storage) {
        ArrayList<Task> filteredTasks = taskList.findByDate(queryDate);
        return Output.printFilteredTaskList(filteredTasks);
    }
}
