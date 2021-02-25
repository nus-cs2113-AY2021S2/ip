package duke;

import duke.main.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> taskList;
    public static int numOfTasks = 0;

    public TaskList(){
        this.taskList = new ArrayList<>();
        this.numOfTasks = taskList.size();
    }

    public static void addTask(Task task) {
        taskList.add(task);
        numOfTasks += 1;
   }

   public static void deleteTask(Task task) {
        taskList.remove(task);
        numOfTasks -= 1;
   }

   public static Task getTask(int index) {
        Task task = taskList.get(index);
        return task;
   }
}
