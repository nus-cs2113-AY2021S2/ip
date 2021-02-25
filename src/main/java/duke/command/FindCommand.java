package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindCommand extends Command{
    public String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeExceptions {
        if (keyword.equals("")) {
            throw new DukeExceptions();
        }
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task: taskList.getTaskList()) {
            if (task.getTaskName().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        ui.showFoundTasks(foundTasks);
    }

    @Override
    public boolean isExit() {
        return exit;
    }
}
