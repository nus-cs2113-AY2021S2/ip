package ip.duke;

import ip.duke.task.Deadline;
import ip.duke.task.Event;
import ip.duke.task.Task;
import ip.duke.task.Todo;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> list;

    /**
     * Constructs a new task list if there is no data stored in the given address.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructs a task list by reading from the file in the given address.
     *
     * @param list the task list that is read from the file in the given address
     */
    public TaskList(ArrayList<Task> list) {
        TaskList.list = list;
    }

    /**
     * Updates a todo type task into the task list and confirms the operation by printing a message
     *
     * @param description the description of this todo task obtained by parsing the user's input command
     */
    public static void updateTodo(String description) {
        list.add(new Todo(description));
        Ui.printRecordMessage(list.get(list.size() - 1));
    }

    /**
     * Updates a deadline type task into the task list and confirms the operation by printing a message
     *
     * @param description the description of this deadline task obtained by parsing the user's input command
     * @param byTime      the latest time of this task should be done obtain by parsing the user's input command
     */
    public static void updateDeadline(String description, String byTime) {
        list.add(new Deadline(description, byTime));
        Ui.printRecordMessage(list.get(list.size() - 1));
    }

    /**
     * Updates an event type task into the task list and confirms the operation by printing a message
     *
     * @param description the description of this deadline task obtained by parsing the user's input command
     * @param atTime      the start time of this task obtain by parsing the user's input command
     */
    public static void updateEvent(String description, String atTime) {
        list.add(new Event(description, atTime));
        Ui.printRecordMessage(list.get(list.size() - 1));
    }

    /**
     * Marks the indicated user's input task as done and confirms the marking operation by printing a message.
     *
     * @param doneIndex the index of the task that to be marked as done
     */
    public static void markDone(int doneIndex) {
        Task doneTask = list.get(doneIndex - 1);
        doneTask.setDone(true);
        list.set(doneIndex - 1, doneTask);
        Ui.printDoneMessage(doneTask);
    }

    /**
     * Deletes the indicated user's input task from the task list and confirms the marking operation by printing a message.
     *
     * @param deletedIndex the index of the task that to be deleted
     */
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

    /**
     * Obtains the tasks that contains the keyword that input by the user and stores them in another task list.
     * Prints the new list obtained before
     * Prints the warning message if there is no matching task in the new task list
     *
     * @param keywordToFind the keyword that the user want all the matching tasks to contain
     */
    public static void getFoundTask(String keywordToFind) {
        ArrayList<Task> foundList = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(keywordToFind)) {
                foundList.add(task);
            }
        }
        if (foundList.size() != 0) {
            Ui.printFoundList(foundList);
        } else {
            Ui.printEmptyMessage();
        }
    }

    /**
     * Obtains the tasks that contains the date that input by the user and stores them in another task list.
     * Prints the new list obtained before
     * Prints the warning message if there is no matching task in the new task list
     *
     * @param dateToFind the date that the user wants all the matching tasks to contain
     */
    public static void getDateTask(String dateToFind) {
        ArrayList<Task> dateList = new ArrayList<>();
        for (Task task : list) {
            if (task.toString().contains(dateToFind)) {
                dateList.add(task);
            }
        }
        if (dateList.size() != 0) {
            Ui.printDateList(dateList);
        } else {
            Ui.printNoMessage();
        }
    }


}
