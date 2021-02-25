package ip.duke;

import ip.duke.task.Deadline;
import ip.duke.task.Event;
import ip.duke.task.Task;
import ip.duke.task.Todo;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        TaskList.list = list;
    }

    public static void updateTodo(String description) {
        list.add(new Todo(description));
        Ui.printRecordMessage(list.get(list.size() - 1));
    }

    public static void updateDeadline(String description, String byTime) {
        list.add(new Deadline(description, byTime));
        Ui.printRecordMessage(list.get(list.size() - 1));
    }

    public static void updateEvent(String description, String atTime) {
        list.add(new Event(description, atTime));
        Ui.printRecordMessage(list.get(list.size() - 1));
    }

    public static void markDone(int doneIndex) {
        Task doneTask = list.get(doneIndex - 1);
        doneTask.setDone(true);
        list.set(doneIndex - 1, doneTask);
        Ui.printDoneMessage(doneTask);
    }

    public static void deleteTask(int deletedIndex) {
        Task deletedTask = list.get(deletedIndex - 1);
        Ui.printDeletedMessage(deletedTask);
        list.remove(deletedIndex - 1);
    }

    public static int getSize() {
        return list.size();
    }

    public static ArrayList<Task> getList() {
        return list;
    }

    public static void getCompleteList() {
        Ui.printList(list);
    }

}
