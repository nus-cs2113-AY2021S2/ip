/*
* The TaskListStores the task list and has operations to add/delete tasks
*/
package tasklist;

import commands.Task;
import java.util.ArrayList;
import ui.Ui;


public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    private int taskCount = 0;
    public static final String border = "    -------------------------------------------------------------------------------------------------------------------------------";
    /*
    * The add task method within the TaskList class adds tasks from the list
    * @params task contains information of the task to be added to the list
    * */
    public void addTask(Task task) {
        tasks.add(task);
    }
    /*
    The deleteTask method within the TaskList class deletes tasks from the list
    * @params taskNum specifies the task to be deleted
    * */
    public void deleteTask(int taskNum) {
        if (taskNum < taskCount && taskNum >= 0) {
            System.out.println(border);
            System.out.println("    Noted. I've removed this task:");
            System.out.println("      " + tasks.get(taskNum));
            System.out.println(" ");
            System.out.println(border);
            tasks.remove(taskNum);
            taskCount--;
        } else {
            System.out.println(border);
            System.out.println("    Index out of range");
            System.out.println(" ");
            System.out.println(border);
        }
    }

    public void incrementTaskCount() {
        taskCount++;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public Task getMostRecentTaskAdded() {
        Task task = tasks.get(taskCount);
        return task;
    }

    public void markTaskAsDone(int taskNum) {
        if (taskNum < taskCount && taskNum >= 0) {
            tasks.get(taskNum).markAsDone();
            System.out.println(border);
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + tasks.get(taskNum));
            System.out.println(" ");
            System.out.println(border);
        } else {
            System.out.println(border);
            System.out.println("    Index out of range");
            System.out.println(" ");
            System.out.println(border);
        }
    }

    public void printList() {
        if (taskCount > 0) {
            System.out.println(border);
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("    " + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println(" ");
            System.out.println(border);
        } else {
            System.out.println(border);
            System.out.println("    The list is empty");
            System.out.println(" ");
            System.out.println(border);
        }
    }

    /*
    * The find method within the TaskList class searches for a keyword in the list of tasks
    * @params keyword takes in a string as the keyword
    * */

    public void find(String keyword) {
        int currentListNumber = 1;
        System.out.println(border);
        System.out.println("    Here are the matching tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            if ((tasks.get(i).getTask()).contains(keyword)) {
                System.out.println("    " + currentListNumber + ". " + tasks.get(i));
                currentListNumber++;
            }
        }
        System.out.println(" ");
        System.out.println(border);
    }

    /*
    * The printCurrentTask method within the TaskList class returns a specified task from the list of tasks
    * @params taskNum specifies the task number of the task to be returned from the list of tasks
    * */
    public Task printCurrentTask(int taskNum) {
        return tasks.get(taskNum);
    }
}
