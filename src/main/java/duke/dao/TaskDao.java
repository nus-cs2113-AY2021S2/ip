package duke.dao;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public interface TaskDao {
    ArrayList<Task> loadAllTasks() throws DukeException;
    void saveAllTasks(ArrayList<Task> tasks);
}
