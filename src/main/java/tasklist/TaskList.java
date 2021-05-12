package tasklist;

import task.Task;

import java.util.ArrayList;

/**
 * Represents the entire task list.
 */
public class TaskList {

    public static ArrayList<Task> allTasks;

    public TaskList(ArrayList<Task> taskList){
        allTasks = taskList;
    }


}
