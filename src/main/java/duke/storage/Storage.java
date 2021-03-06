package duke.storage;

import duke.dao.TaskDaoImpl;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Class that represents the storage load/save access for Duke.
 */
public class Storage {
    private TaskDaoImpl taskDao;

    /**
     * Sole constructor. Creates a data access object with the given file path.
     * @param filePath the path of the file used for storage
     */
    public Storage(String filePath) {
        this.taskDao = new TaskDaoImpl(filePath);
    }

    /**
     * Loads all tasks from storage file
     * @return an array list of tasks that was loaded
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        return taskDao.loadAllTasks();
    }

    /**
     * Saves all tasks to storage file
     * @param tasks the array list of tasks to be stored
     */
    public void save(ArrayList<Task> tasks) {
        taskDao.saveAllTasks(tasks);
    }
}
