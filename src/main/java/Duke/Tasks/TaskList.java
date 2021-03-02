package Duke.Tasks;

import java.io.IOException;
import java.util.ArrayList;

import Duke.Exceptions.*;
import Duke.Storage.Storage;
import Duke.Ui.Ui;


/**
 * Represents a list of Task
 * The ArrayList of Task <code>tasks</code> represents the list of all the tasks available
 * This class is responsible for dealing with the various commands that changes the tasklists.
 * it holds commands like add, delete, find etc.
 */

public class TaskList {
    public ArrayList<Task> tasksLists;
    protected Storage storage = new Storage("data/tasksList.txt");

    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasksLists = loadedTasks;
    }

    /**
     * Add task into array list
     *
     * @param task type of ask added
     * @throws IOException if tasks cannot be write to storage.
     */

    public void addTask(Task task) throws IOException {
        task.toString();
        tasksLists.add(task);
        storage.writeToFile(tasksLists);
        Ui.printAddTask(tasksLists, task);
    }

    /**
     * Parsing Event task to be added
     *
     * @param inputs String from user input
     * @return a String to show that event has been added
     * @throws IOException if tasks cannot be write to storage.
     * @throws WrongEventFormatException if event is passed in the wrong format
     */

    public String addEvent(String inputs) throws WrongEventFormatException, IOException {
        if (!inputs.matches(".+/at.+")) throw new WrongEventFormatException();

        String command = inputs.substring(inputs.indexOf(" ") + 1);
        String title = command.substring(0, command.indexOf("/") - 1);
        String date = inputs.substring(inputs.indexOf("/at") + 4);

        Event newEvent = new Event(title, date);
        addTask(newEvent);

        return "Event added!";
    }

    /**
     * Parsing Deadline task to be added
     *
     * @param inputs String from user input
     * @return a String to show that deadline has been added
     * @throws IOException if tasks cannot be write to storage.
     * @throws WrongDeadlineFormatException if event is passed in the wrong format
     */

    public String addDeadline(String inputs) throws WrongDeadlineFormatException, IOException {
        if (!inputs.matches(".+/by.+")) throw new WrongDeadlineFormatException();

        String command = inputs.substring(inputs.indexOf(" ") + 1);
        String title = command.substring(0, command.indexOf("/") - 1);
        String date = inputs.substring(inputs.indexOf("/by") + 4);

        Deadline newDeadline = new Deadline(title, date);
        addTask(newDeadline);

        return "Deadline added!";
    }

    /**
     * Parsing Todo task to be added
     *
     * @param inputs String from user input
     * @return a String to show that todo has been added
     * @throws IOException if tasks cannot be write to storage.
     * @throws WrongToDoFormatException if event is passed in the wrong format
     */

    public String addTodo(String inputs) throws WrongToDoFormatException, IOException {
        String title = inputs.substring(inputs.indexOf(" ") + 1);
        if (inputs.equals("todo")) throw new WrongToDoFormatException();

        Todo newTodo = new Todo(title);
        addTask(newTodo);

        return "Todo added!";
    }

    /**
     * Marking Task as Done
     *
     * @param num To determine which task in the list is getting marked
     * @return a String to display that item is marked as done.
     * @throws IOException if tasksList cannot be write to storage.
     * @throws NoSuchTaskException when there is no such task number in the task list.
     */

    public String markAsDone(int num) throws NoSuchTaskException, IOException {
        if (!isValidTaskNumber(num)) {
            throw new NoSuchTaskException();
        }
        tasksLists.get(num - 1).markAsDone();
        storage.writeToFile(tasksLists);
        return Ui.printDone(tasksLists, num);
    }

    /**
     * Deleting Task from Array List
     *
     * @param taskNumber To determine which task in the list is getting deleted.
     * @return a String to display that item has been deleted.
     * @throws IOException if tasksList cannot be write to storage.
     * @throws NoSuchTaskException when there is no such task number in the task list.
     */

    public String deleteTask(int taskNumber) throws NoSuchTaskException, IOException {
        if (!isValidTaskNumber(taskNumber)) {
            throw new NoSuchTaskException();
        }
        Task toDelete = tasksLists.get(taskNumber - 1);
        tasksLists.remove(taskNumber - 1);
        storage.writeToFile(tasksLists);
        return Ui.printDelete(toDelete, tasksLists);
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= tasksLists.size();
    }


    /**
     * Gets the size of the task list.
     *
     * @return size of task list.
     */

    public int size() {
        return tasksLists.size();
    }

    /**
     * Gets the index of the task in the taskList
     *
     * @return index number of task.
     */

    public Task getTaskIndex(int index) {
        return tasksLists.get(index);
    }

    /**
     * Adds the tasks with keyword to a separate array to be display search result.
     *
     * @param inputs keyword to be search
     * @return a string to determine that a task is found successfully.
     */

    public String findTasks(String inputs) {
        String keyWord = inputs.substring(inputs.indexOf(" ") + 1);

        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasksLists) {
            if (task.getDescription().contains(keyWord)) {
                foundTasks.add(task);
            }
        }
        Ui.printFind(foundTasks);
        return "Task found!";
    }
}
