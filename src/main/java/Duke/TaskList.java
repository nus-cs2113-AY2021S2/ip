package Duke;

import Duke.Tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(Task inputTask) {
        tasks.add(inputTask);
    }

    public Task updateCompletion(int taskIndex) {
        tasks.get(taskIndex - 1).setDone();
        return tasks.get(taskIndex - 1);
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task deleteTask(int taskIndex) {
        Task deletedTask = tasks.remove(taskIndex - 1);
        return deletedTask;
    }

    public ArrayList<Task> findByDate(LocalDate queryDate) {
        ArrayList<Task> tasksWithDate = new ArrayList<Task>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.getDate() != null && currentTask.getDate().toString().equals(queryDate.toString())) {
                tasksWithDate.add(currentTask);
            }
        }
        return tasksWithDate;
    }

    public ArrayList<Task> returnTaskList() {
        return tasks;
    }

    public ArrayList<Task> returnFilteredTaskList(String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String nameOfTask = currentTask.getName();
            if (nameOfTask.contains(keyword)) {
                tasksWithKeyword.add(currentTask);
                continue;
            }
        }
        return tasksWithKeyword;
    }

}