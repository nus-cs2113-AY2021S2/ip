package duke.dao;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Data access object interface for Tasks
 */
public interface TaskDao {
    ArrayList<Task> loadAllTasks() throws DukeException;
    void saveAllTasks(ArrayList<Task> tasks);
}
