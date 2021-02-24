package duke.command;

import duke.storage.Storage;
import duke.task.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UpdateCommand extends Command {
    List<String> indexList;

    public UpdateCommand(List<String> indexList) {
        super();
        this.indexList = indexList;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeExceptions {
        ArrayList<Integer> integerIndexList = new ArrayList<>();
        ArrayList<Task> updatedTaskList = new ArrayList<>();
        for (String index: indexList) {
            int indexInt;
            indexInt = Integer.parseInt(index) - 1;
            if (indexInt >= taskList.getSize()) {
                throw new DukeExceptions();
            }
            integerIndexList.add(indexInt);
        }
        for (int index: integerIndexList) {
            taskList.markIsCompletedTask(index, true);
            updatedTaskList.add(taskList.getTask(index));
        }
        storage.save(taskList);
        ui.showUpdatedTasks(updatedTaskList);
    }

    @Override
    public boolean isExit() {
        return exit;
    }
}
