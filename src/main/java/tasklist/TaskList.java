/*
 * The TaskListStores the task list and has operations to add/delete tasks
 */
package tasklist;

import commands.Task;

import java.util.ArrayList;

import constants.Constants;
import ui.Ui;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    private static Constants constants = new Constants();
    private int taskCount = 0;

    /*
     * The add task method adds a task to the list
     * @params task contains information of the task to be added to the list
     * */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(constants.border);
        System.out.println("Got it. I've added this task:");
        System.out.println(getMostRecentTaskAdded());
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(constants.border);
    }

    /*
    The deleteTask method deletes a task from the list
    * @params taskNum specifies the task number of the task to be deleted
    * */
    public void deleteTask(int taskNum) {
        if (taskNum < taskCount && taskNum >= 0) {
            System.out.println(constants.border);
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(taskNum));
            System.out.println(constants.border);
            tasks.remove(taskNum);
            taskCount--;
        } else {
            System.out.println(constants.border);
            System.out.println("Index out of range");
            System.out.println(constants.border);
        }
    }

    /*
     * The getTaskCount method retrieves the current total number of tasks on the list
     * */
    public int getTaskCount() {
        return taskCount;
    }

    public Task getMostRecentTaskAdded() {
        Task task = tasks.get(taskCount);
        return task;
    }

    /*
     * The markTaskAsDone method marks a task on the list as done
     * @params taskNum of the task to be marked as done
     * */
    public void markTaskAsDone(int taskNum) {
        try {
            tasks.get(taskNum).markAsDone();
            System.out.println(constants.border);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskNum));
            System.out.println(constants.border);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(constants.indexOutOfBoundsMessage);
        }
    }

    /*
     * The showList method displays the current list
     * */
    public void showList() {
        if (taskCount > 0) {
            System.out.println(constants.border);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            System.out.println(constants.border);
        } else {
            System.out.println(constants.border);
            System.out.println("The list is empty");
            System.out.println(constants.border);
        }
    }

    /*
     * The find method searches for a keyword in the list of tasks
     * @params keyword takes in a string as the keyword to be searched for in the list of tasks
     * */
    public void find(String keyword) {
        try {
            int currentListNumber = 1;
            System.out.println(constants.border);
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                if ((tasks.get(i).getTask()).contains(keyword)) {
                    System.out.println("    " + currentListNumber + ". " + tasks.get(i));
                    currentListNumber++;
                }
            }
            System.out.println(constants.border);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please specify a keyword to find");
        }
    }

    /*
     * The getCurrentTask method returns a specific task from the list of tasks
     * @params taskNum specifies the task number of the task to be returned from the list of tasks
     * */
    public Task getCurrentTask(int taskNum) {
        return tasks.get(taskNum);
    }
}
