package duke;

import duke.tasksmanager.Tasks;
import java.util.ArrayList;

import static duke.Storage.tasksCount;

/**
 * <code>TaskList</code> object represents the list to store all tasks.
 */
public class TaskList {

    public static ArrayList<Tasks> tasks;

    public TaskList() {
        this(new ArrayList<>());
    }
    
    public TaskList(ArrayList<Tasks> prevTaskList) {
        tasks = new ArrayList<>(); //for storing (all types of) tasks
        if (prevTaskList.size() > 0){
            for (Tasks prevTask:prevTaskList) {
                tasks.add(prevTask);
            }
        }
    }

    /**
     * Adds new tasks into current 'tasks' list using the index.
     * It also increments the overall variable tasksCount.
     * @param currentTask <code>TaskList</code> object given by user to be added to the current list.
     */
    public static void add(Tasks currentTask) {
        tasks.add(currentTask);
        tasksCount++;
    }

    /**
     * Removes existing tasks from the current 'tasks' list using the index.
     * It also decrements the overall variable tasksCount.
     * @param index position of the task in the 'tasks' list.
     */
    public static void remove(int index) {
        tasks.remove(index);
        tasksCount--;
    }

    /**
     * Returns task with given 'index' position in the 'tasks' list.
     * Complements methods for 'delete' and 'done' commands.
     *
     * @return task with 'index' position in the TaskList
     */
    public static Tasks get(int index) {
        return tasks.get(index);
    }

}
