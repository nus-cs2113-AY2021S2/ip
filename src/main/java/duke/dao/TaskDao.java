package duke.dao;

import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public interface TaskDao extends Serializable {
    ArrayList<Task> loadAllTasks();
    void saveAllTasks(ArrayList<Task> tasks);
}
