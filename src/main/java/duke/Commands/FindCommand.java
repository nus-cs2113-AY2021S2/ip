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

    /**
     * Finds the <code>Task</code> objects that contains a <code>KEYWORD</code> in their description
     * @param taskList The list of tasks stored in Duke
     * @param ui The <code>Ui</code> object that shows the output to the user
     * @param storage The <code>Storage</code> object that saves the updated tasks to the file
     */
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
