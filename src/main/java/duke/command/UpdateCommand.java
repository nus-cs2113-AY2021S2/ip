package duke.command;

import duke.storage.Storage;
import duke.task.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the executor to update a list of tasks as completed
 */
public class UpdateCommand extends Command {
    List<String> indexList;

    /**
     * Constructor of the class. Initializes the indexes of tasks to be updated
     * @param indexList The list of indexes provided by the user
     */
    public UpdateCommand(List<String> indexList) {
        super();
        this.indexList = indexList;
    }

    /**
     * Updates tasks specified by the user to completed
     * @param taskList TaskList object that stores all current tasks
     * @param storage Storage object that communicates with database
     * @param ui Ui object that handles the interaction with user
     * @throws DukeExceptions if invalid indexes provided
     */
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

    /**
     * Checks if we want to exit the program
     * @return whether or not to exit the program
     */
    @Override
    public boolean isExit() {
        return exit;
    }
}
