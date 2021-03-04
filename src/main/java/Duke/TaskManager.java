package Duke;

import java.util.ArrayList;


public class TaskManager {
    private static ArrayList<Task> tasksList = new ArrayList<>();

    public TaskManager() {
        tasksList = new ArrayList<>();
    }

    public Task addTask (Task toAdd) {
        tasksList.add(toAdd);
        return toAdd;
    }

    public int taskCount () {
        return tasksList.size();
    }

    public Task removeTask (String taskNo) {
        int taskNumber = Integer.parseInt(taskNo);
        taskNumber--;
        Task selectedTask = tasksList.get(taskNumber);
        tasksList.remove(taskNumber);
        return selectedTask;
    }

    public Task getTask (String taskNo) {
        int taskNumber = Integer.parseInt(taskNo);
        taskNumber--;
        Task selectedTask = tasksList.get(taskNumber);
        return selectedTask;
    }
}