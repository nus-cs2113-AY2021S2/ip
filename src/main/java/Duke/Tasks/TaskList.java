package Duke.Tasks;

import java.io.IOException;
import java.util.ArrayList;

import Duke.Exceptions.*;
import Duke.Storage.Storage;
import Duke.Ui.Ui;

/*
@ This class is responsible for dealing with the various commands that changes the tasklists.
@ it holds commands like add, delete, find etc.
@ the addTask method is further broken down into addDeadline, addEvent, addTodo,
@ these various methods breaks down the user input so that it fits the format to be added into the
@ array list in addTask. addTask also calls the method storage.writeTofile which clears the file and rewrite
@ with the current content of the file.
@ Similarly for deleteTask, the task to be deleted is removed from the arrayList and a storage.writetofile is called
@ to rewrite the content of the file to match the current arrayList
@ findTask is a method that iterates through the current arraylist and adds any task that contains the keyword into
@ another arraylist which is passed to the UI to print.
*/


public class TaskList {
    public ArrayList<Task> tasksLists;
    protected Storage storage = new Storage("data/tasksList.txt");

    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasksLists = loadedTasks;
    }


    public void addTask(Task task) throws IOException {
        task.toString();
        tasksLists.add(task);
        storage.writeToFile(tasksLists);
        Ui.printAddTask(tasksLists, task);
    }

    public String addEvent(String inputs) throws WrongEventFormatException, IOException {
        if (!inputs.matches(".+/at.+")) throw new WrongEventFormatException();

        String command = inputs.substring(inputs.indexOf(" ") + 1);
        String title = command.substring(0, command.indexOf("/") - 1);
        String date = inputs.substring(inputs.indexOf("/at") + 4);

        Event newEvent = new Event(title, date);
        addTask(newEvent);

        return "Event added!";
    }

    public String addDeadline(String inputs) throws WrongDeadlineFormatException, IOException {
        if (!inputs.matches(".+/by.+")) throw new WrongDeadlineFormatException();

        String command = inputs.substring(inputs.indexOf(" ") + 1);
        String title = command.substring(0, command.indexOf("/") - 1);
        String date = inputs.substring(inputs.indexOf("/by") + 4);

        Deadline newDeadline = new Deadline(title, date);
        addTask(newDeadline);

        return "Deadline added!";
    }

    public String addTodo(String inputs) throws WrongToDoFormatException, IOException {
        String title = inputs.substring(inputs.indexOf(" ") + 1);
        if (inputs.equals("todo")) throw new WrongToDoFormatException();

        Todo newTodo = new Todo(title);
        addTask(newTodo);

        return "Todo added!";
    }

    public String markAsDone(int num) throws NoSuchTaskException, IOException {
        if (!isValidTaskNumber(num)) {
            throw new NoSuchTaskException();
        }
        tasksLists.get(num - 1).markAsDone();
        storage.writeToFile(tasksLists);
        return Ui.printDone(tasksLists, num);
    }

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

    public void clearTaskList() {
        tasksLists.clear();
    }

    public int size() {
        return tasksLists.size();
    }

    public Task get(int index) {
        return tasksLists.get(index);
    }

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
