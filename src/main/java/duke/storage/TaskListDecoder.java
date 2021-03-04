package duke.storage;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;

import java.util.ArrayList;

/**
 * Decodes the storage data file into an Task item arraylist.
 */
public class TaskListDecoder {

    public static ArrayList<Task> decoder(ArrayList<String> input) {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (String s : input) {
            //create tasklist according to input
            String[] arr = s.split("\\|");
            switch (arr[0]) {
                case "T":
                    taskArrayList.add(new Todo(arr[2]));
                    break;
                case "D":
                    taskArrayList.add(new Deadline(arr[2], arr[3]));
                    break;
                case "E":
                    taskArrayList.add(new Event(arr[2], arr[3]));
                    break;
                default:
            }
        }
        return taskArrayList;
    }
}
