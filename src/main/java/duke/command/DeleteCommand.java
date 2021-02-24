package duke.command;

import duke.storage.Storage;
import duke.task.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteCommand extends Command {
    public List<String> indexList;

    public DeleteCommand(List<String> indexList) {
        super();
        this.indexList = indexList;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeExceptions {
        if (indexList.size() < 1) {
            throw new DukeExceptions();
        }
        ArrayList<Integer> integerIndexList = new ArrayList<>();
        ArrayList<Task> deletedTaskList = new ArrayList<>();
        for (String index: indexList) {
            int indexInt;
            indexInt = Integer.parseInt(index) - 1;
            if (indexInt >= taskList.getSize()) {
                throw new DukeExceptions();
            }
            integerIndexList.add(indexInt);
        }
        Collections.sort(integerIndexList, Collections.reverseOrder());
        for (int index: integerIndexList) {
            deletedTaskList.add(taskList.getTask(index));
            taskList.deleteTask(index);
        }
        storage.save(taskList);
        ui.showDeletedTasks(deletedTaskList, taskList.getSize());
    }

    @Override
    public boolean isExit() {
        return exit;
    }
}
