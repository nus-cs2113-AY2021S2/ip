package duke;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;


    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public static void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    public static int getListSize() {
        return taskList.size();
    }

    public static Task getTaskAtIndex(int index) {
        return taskList.get(index);
    }

}
