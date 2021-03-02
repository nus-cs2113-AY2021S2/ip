package tasklist;

import java.util.ArrayList;
import static common.Messages.RECEIVE_MESSAGE;
import static common.Messages.GETSIZE_MESSAGE;
public class Tasklist {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static Boolean isCorrupted(Task task) {
        return (task.isCorrupted == -1);
    }
    public static void addTask(Task task) {
        tasks.add(task);
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

    public static boolean isEmpty() {
        if (Tasklist.getSize() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static void markTaskAsDone(int index) {
        Task task = Tasklist.getIndex(index);
        task.isDone = true;
    }
}
