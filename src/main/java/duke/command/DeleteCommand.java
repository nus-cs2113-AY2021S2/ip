package duke.command;

import duke.storage.Storage;
import duke.task.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the executor that will delete tasks from task list
 */
public class DeleteCommand extends Command {
    public List<String> indexList;

    /**
     * Constructor of this class. Initializes the tasks to be deleted
     * @param indexList The list indexes of tasks to be deleted
     */
    public DeleteCommand(List<String> indexList) {
        super();
        this.indexList = indexList;
    }

    /**
     * Deletes tasks from task list
     * @param taskList TaskList object that stores all current tasks
     * @param storage Storage object that communicates with database
     * @param ui Ui object that handles the interaction with user
     * @throws DukeExceptions if invalid indexes provided or if no indexes provided
     */
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

    /**
     * Checks if we want to exit the program
     * @return whether or not to exit the program
     */
    @Override
    public boolean isExit() {
        return exit;
    }
}
