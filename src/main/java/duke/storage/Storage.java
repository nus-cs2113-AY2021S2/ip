package duke.storage;

import duke.dao.TaskDaoImpl;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class Storage {
    private TaskDaoImpl taskDao;

    public Storage(String filePath) {
        this.taskDao = new TaskDaoImpl(filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        return taskDao.loadAllTasks();
    }

    public void save(ArrayList<Task> tasks) {
        taskDao.saveAllTasks(tasks);
    }
}
