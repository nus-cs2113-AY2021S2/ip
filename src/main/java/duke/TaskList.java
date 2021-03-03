package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Stores an array of tasks, provides methods to edit tasks in the tasklist.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList objects, can be initialised with existing array retrieved from Storage.
     *
     * @param tasks Existing array of tasks, parsed from storage file.
     */
    public TaskList(ArrayList<Task> tasks) {
        if (tasks.size() > 0) {
            this.tasks = tasks;
        } else {
            this.tasks = new ArrayList<>();
        }
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Prints out numbered tasks in the following format: [taskType][done/not done] description (at/by: deadline/timeslot).
     */
    public void listTasks(){
        if (Task.taskCount == 0) {
            System.out.println("No tasks yet!");
        } else {
            for (int i = 0; i < Task.taskCount; i++) {
                System.out.printf("%d.%s\n", i+1, tasks.get(i).toString());
            }
        }
    }

    /**
     * Returns current number of tasks in the tasklist.
     *
     * @return number of tasks.
     */
    public int getTaskCount() {
        return Task.taskCount;
    }

    public void printNumTasks() {
        if (getTaskCount() == 0) {
            System.out.println("No tasks yet!");
        } else {
            System.out.println("You now have " + getTaskCount() + " tasks in your tasklist.");
        }
    }

    /**
     * Deletes tasks given an ArrayList of task indexes.
     *
     * @param indexes ArrayList of indexes extracted from user input.
     */
    public void deleteTasks(ArrayList<Integer> indexes) {
        if (indexes.size() == 0) {
            return;
        }
        if (indexes.size() > 1) {
            System.out.println("Okay, I've deleted these tasks:");
        } else {
            System.out.println("Okay, I've deleted this task:");
        }
        for (Integer index : indexes) {
            System.out.println(tasks.get(index).toString());
            Task.taskCount -= 1;
            tasks.remove(index.intValue());
        }
        printNumTasks();
    }

    /**
     * Marks tasks at the given indexes as done.
     *
     * @param indexes ArrayList of indexes extracted from user input.
     */
    public void markTasksAsDone(ArrayList<Integer> indexes) {
        for (Integer index : indexes) {
            tasks.get(index).markAsDone();
        }
    }

    /**
     * Adds new task object to the TaskList.
     * Prints the task added and the new number of tasks in the tasklist.
     *
     * @param newTask Instantiated task.
     */
    public void addTask(Task newTask) {
        tasks.add(Task.taskCount-1, newTask); //taskCount was incremented before adding to tasklist
        System.out.println("I have added this task:" );
        System.out.println(tasks.get(Task.taskCount-1).toString());
        printNumTasks();
    }
}
