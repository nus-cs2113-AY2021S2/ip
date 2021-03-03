package duke;

import duke.tasksmanager.Tasks;
import java.util.ArrayList;

import static duke.Storage.tasksCount;

/**
 * TaskList Class: Type for List storing all the tasks
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
     * for adding new tasks into current TaskList
     * @param currentTask
     */
    public static void add(Tasks currentTask) {
        tasks.add(currentTask);
        tasksCount++;
    }

    /**
     * for removing new tasks from current TaskList
     * @param index - remove tasks by their position in the TaskList
     */
    public static void remove(int index) {
        tasks.remove(index);
        tasksCount--;
    }

    /**
     * @return task with 'index' position in the TaskList
     */
    public static Tasks get(int index) {
        return tasks.get(index);
    }

}
