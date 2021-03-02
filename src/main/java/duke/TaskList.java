package duke;

import duke.tasksmanager.Tasks;
import java.util.ArrayList;

import static duke.Storage.tasksCount;

public class TaskList {

    public static ArrayList<Tasks> tasks;

    public TaskList() {
        this(new ArrayList<>());
        //tasksCount = 0;
    }

    public TaskList(ArrayList<Tasks> prevTaskList) {
        tasks = new ArrayList<>(); //for storing (all types of) tasks
        if (prevTaskList.size() > 0){
            for (Tasks prevTask:prevTaskList) {
                tasks.add(prevTask);
            }
        }
    }

    public static void add(Tasks currentTask) {
        tasks.add(currentTask);
        tasksCount++;
    }

    public static void remove(int index) {
        tasks.remove(index);
        tasksCount--;
    }

    public static Tasks get(int index) {
        return tasks.get(index);
    }

}
