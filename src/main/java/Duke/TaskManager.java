package Duke;

import java.util.ArrayList;


public class TaskManager {
    private static ArrayList<Task> tasksList = new ArrayList<>();

    public TaskManager() {
        tasksList = new ArrayList<>();
    }

    public Task addTask (Task toAdd) {
        tasksList.add(toAdd);
        return toAdd;
    }

    public static int taskCount() {
        return tasksList.size();
    }

    public static Task removeTask(String taskNo) {
        int taskNumber = Integer.parseInt(taskNo);
        taskNumber--;
        Task selectedTask = tasksList.get(taskNumber);
        tasksList.remove(taskNumber);
        return selectedTask;
    }

    public static Task getTask(String taskNo) {
        int taskNumber = Integer.parseInt(taskNo);
        taskNumber--;
        return tasksList.get(taskNumber);
    }

    public static Task getTaskWithInt(Integer taskNo) {
        taskNo--;
        return tasksList.get(taskNo);
    }

    public static void listOutTasks() {
        DukeUI.printLine();
        int i = 0;
        while (i <taskCount()) {
            i++;
            Task selectedTask = getTaskWithInt(i);
            DukeUI.print(i + ". " + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        }
    }

    public static void deleteTask(String s) {
        Task selectedTask = getTask(s);
        DukeUI.print("Noted. I've removed this task");
        DukeUI.print("\t" + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        removeTask(s);
        DukeUI.print("Now you have " + TaskManager.taskCount() + " tasks in the list.");
    }

    public static void markTaskAsDone(String s) {
        Task selectedTask = TaskManager.getTask(s);
        selectedTask.markAsDone();
        DukeUI.print("Nice! Following task is now marked as done:");
        DukeUI.print("[X] " + selectedTask.getDescription());
    }

    public ArrayList<Task> returnTaskList() {
        return tasksList;
    }
}