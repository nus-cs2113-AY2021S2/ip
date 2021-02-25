package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Create a taskList and a counter for number of tasks
 */
public class TaskList {
    public static ArrayList<Task> taskList;
    public static int numOfTasks = 0;

    /**
     * Create a taskList and a counter for number of tasks
     */
    public TaskList(){
        this.taskList = new ArrayList<>();
        this.numOfTasks = taskList.size();
    }

    /**
     * Add task to taskList and increase the counter
     *
     * @param task
     */
    public static void addTask(Task task) {
        taskList.add(task);
        numOfTasks += 1;
   }

    /**
     * Delete task from taskList and decrease the counter
     *
     * @param index
     * @return task deleted
     */
   public static Task deleteTask(int index) {
        Task task = TaskList.getTask(index);
        taskList.remove(task);
        numOfTasks -= 1;
        return task;
   }

    /**
     * Find if task in taskList contains the keyword
     *
     * @param keyword
     * @param index
     * @return whether task contains the keyword
     */
    public static boolean findTask(String keyword, int index) {
        Task task = taskList.get(index);
        return task.description.contains(keyword);
    }

    /**
     * Get task in taskList if the requested index
     *
     * @param index
     * @return task
     */
   public static Task getTask(int index) {
        Task task = taskList.get(index);
        return task;
   }


}
