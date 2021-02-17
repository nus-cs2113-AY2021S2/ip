package duke.dao;

import duke.task.Task;

import java.util.ArrayList;

public interface TaskDao {
    ArrayList<Task> loadAllTasks();
    void saveAllTasks(ArrayList<Task> tasks);
}
