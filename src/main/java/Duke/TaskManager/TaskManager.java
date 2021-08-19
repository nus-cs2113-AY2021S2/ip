package Duke.TaskManager;

import Duke.Task.Task;
import Duke.UI.DukeUI;

import java.util.ArrayList;


public class TaskManager {
    private static ArrayList<Task> tasksList = new ArrayList<>();

    public TaskManager() {
        tasksList = new ArrayList<>();
    }

    /**
     * Add new Task object into tasksList
     *
     * @param toAdd Task object
     * @return Task object that has been added
     */
    public Task addTask (Task toAdd) {
        tasksList.add(toAdd);
        return toAdd;
    }

    /**
     * return the number of tasks in tasksList
     *
     * @return
     */
    public static int taskCount() {
        return tasksList.size();
    }

    /**
     * remove specific Task object from ArrayList
     *
     * @param taskNo index of the Task to be removed
     * @return The Task object that has just been removed
     */
    public static Task removeTask(String taskNo) {
        int taskNumber = Integer.parseInt(taskNo);
        taskNumber--;
        Task selectedTask = tasksList.get(taskNumber);
        tasksList.remove(taskNumber);
        return selectedTask;
    }


    /**
     * returns specific Task from tasksList when the input is a string
     *
     * @param taskNo index of the Task from ArrayList
     * @return The Task object from that index
     */
    public static Task getTask(String taskNo) {
        int taskNumber = Integer.parseInt(taskNo);
        taskNumber--;
        return tasksList.get(taskNumber);
    }

    /**
     * returns specific Task from tasksList when the input is an integer
     *
     * @param taskNo index of the Task from ArrayList
     * @return The Task object from that index
     */
    public static Task getTaskWithInt(Integer taskNo) {
        taskNo--;
        return tasksList.get(taskNo);
    }

    /**
     * List out all the tasks and details through DukeUI
     */
    public static void listOutTasks() {
        DukeUI.printLine();
        int i = 0;
        while (i <taskCount()) {
            i++;
            Task selectedTask = getTaskWithInt(i);
            DukeUI.print(i + ". " + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        }
    }

    /**
     * Call removeTask method and notify user of deletion and remaining number of Tasks
     *
     * @param s index of Task to be removed
     */
    public static void deleteTask(String s) {
        Task selectedTask = getTask(s);
        DukeUI.print("Noted. I've removed this task");
        DukeUI.print("\t" + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        removeTask(s);
        DukeUI.print("Now you have " + TaskManager.taskCount() + " tasks in the list.");
    }

    /**
     * Set the Task.isDone as True
     *
     * @param s index of the Task that will be marked as Done
     */
    public static void markTaskAsDone(String s) {
        Task selectedTask = TaskManager.getTask(s);
        selectedTask.markAsDone();
        DukeUI.print("Nice! Following task is now marked as done:");
        DukeUI.print("[X] " + selectedTask.getDescription());
    }

    /**
     * List out the tasks that contains the keyword s.
     *
     * @param s keyword input by the user
     */
    public static void findTask(String s) {
        ArrayList<Task> resultList = new ArrayList<>();
        int i = 0;
        while (i < taskCount()) {
            i++;
            Task selectedTask = getTaskWithInt(i);
            String[] listOfDescriptions = selectedTask.getDescription().split(" ");
            for(String words : listOfDescriptions) {
                // DukeUI.print(words);
                if (words.equalsIgnoreCase(s)) {
                    resultList.add(selectedTask);
                }
            }
        }
        int j = 0;
        DukeUI.print("Here are the matching tasks in your list:");
        DukeUI.printLine();
        if (resultList.size() == 0) {
            DukeUI.print("No results.");
        } else {
        while (j < resultList.size()) {
            Task selectedTask = resultList.get(j);
            j++;
            DukeUI.print(j + ". " + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        }}
    }

    public ArrayList<Task> returnTaskList() {
        return tasksList;
    }
}