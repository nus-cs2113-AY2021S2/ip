package duke.Commands;

import duke.Storage;
import duke.TaskList;
import duke.Tasks.Task;
import duke.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    private final String KEYWORD;

    public FindCommand(String KEYWORD) {
        this.KEYWORD = KEYWORD;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            if (task.getDescription().contains(KEYWORD)) {
                filteredTaskList.add(task);
            }
        }
        ui.showFindTasks(filteredTaskList, KEYWORD);
    }
}
