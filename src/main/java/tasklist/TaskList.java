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
    public static final String border = "    ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――";

    public void addTask(Task task) {
        tasks.add(task);
    }

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
        if(taskCount>0) {
            System.out.println(border);
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("    " + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println(" ");
            System.out.println(border);
        }
        else{
            System.out.println(border);
            System.out.println("    The list is empty");
            System.out.println(" ");
            System.out.println(border);
        }
    }

    public Task printCurrentTask(int taskNum) {
        return tasks.get(taskNum);
    }
}
