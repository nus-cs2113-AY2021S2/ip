package tasklist;

import java.util.ArrayList;
import static common.Messages.RECEIVE_MESSAGE;
import static common.Messages.GETSIZE_MESSAGE;
public class Tasklist {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println((RECEIVE_MESSAGE));
    }

    public static void removeTask(Task task) {
        tasks.remove(task);
    }

    public static ArrayList<Task> viewTasks() {
        return tasks;
    }

    public static Task getIndex(int index) {
        return tasks.get(index);
    }

    public static int getSize() {
        //System.out.println(GETSIZE_MESSAGE);
        return tasks.size();
    }
}
