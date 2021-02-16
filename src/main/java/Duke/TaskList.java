package Duke;

import Duke.Tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(Task inputTask) {
        tasks.add(inputTask);
    }

    public void addAndPrint(Task inputTask) {
        addTask(inputTask);
        Output.printAdded(inputTask, tasks.size());
    }

    public void updateCompletion(int taskIndex) {
        tasks.get(taskIndex - 1).setDone();
        Output.printUpdated(tasks.get(taskIndex - 1).toString());
    }


    public void printTaskList() {
        Output.printTaskList(tasks);
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void deleteTask(int taskIndex) {
        Output.printDeleted(tasks.get(taskIndex - 1).toString(), tasks.size() - 1);
        tasks.remove(taskIndex - 1);
    }

}