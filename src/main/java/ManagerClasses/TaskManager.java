package ManagerClasses;

import TaskClasses.Deadline;
import TaskClasses.Event;
import TaskClasses.Task;
import TaskClasses.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the task actions that the user may input.
 */
public class TaskManager {
    private final List<Task> tasks;

    /**
     * Constructor for the TaskManager class.
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * Getter for the tasks list.
     * @return the task list in the taskManager
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Shows the tasks in the task list.
     */
    public void showTasks() {
        if (tasks.size() == 0) {
            System.out.println("Oops, it seems like you don't have any tasks.");
        } else {
            int taskId = 1;
            System.out.println("Here are the tasks in your list:");
            for (Task task: tasks) {
                System.out.println(taskId + ". " + task.toString());
                taskId++;
            }
        }
    }

    /**
     * Marks the specified task as being done.
     * @param taskIdNum index number of the task that is to be marked as done.
     */
    public void markDone(Integer taskIdNum) {
//        Check if user input for 'done' task id is within the range of the list.
        try {
            if (taskIdNum != null) {
                int taskListIndexNum = taskIdNum - 1;
                Task task = tasks.get(taskListIndexNum);
                task.setAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.toString());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error -> Cannot find task with the specified task number " + taskIdNum + ".");
        }
    }

    /**
     * Deletes the specified task from the task list.
     * @param taskIdNum index number of the task that is to be deleted.
     */
    public void deleteTask (Integer taskIdNum) {
        try {
            if (taskIdNum != null) {
                int taskListIndexNum = taskIdNum - 1;
                Task task = tasks.get(taskListIndexNum);
                String taskDescription = task.toString();
                tasks.remove(taskListIndexNum);
                System.out.println("Nice! I've removed this task:");
                System.out.println(taskDescription);
                printNumberOfTasks();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error -> Cannot find task with the specified task number " + taskIdNum + ".");
        }
    }

    /**
     * Converts the inputs into a todo task and calls the addTaskToList() method to add the task into the task list.
     * @param description description of the todo task.
     */
    public void addTodoToList(String description) {
        Todo todoTask = new Todo(description);
        addTaskToList(todoTask);
    }

    /**
     * Converts the inputs into a event task and calls the addTaskToList() method to add the task into the task list.
     * @param description description of the event task.
     * @param at when is the event going to take place.
     */
    public void addEventToList(String description, String at) {
        Event eventTask = new Event(description, at);
        addTaskToList(eventTask);
    }

    /**
     * Converts the inputs into a deadline task and calls the addTaskToList() method to add the task into the task list.
     * @param description description of the deadline task.
     * @param by when must the task be done by.
     */
    public void addDeadlineToList(String description, String by) {
        Deadline deadlineTask = new Deadline(description, by);
        addTaskToList(deadlineTask);
    }

    /**
     * Add task into the task list.
     * @param task the task that is to be added to the task list.
     */
    private void addTaskToList(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        printNumberOfTasks();
    }

    /**
     * Prints the number of tasks in the task list into the terminal console.
     */
    public void printNumberOfTasks() {
        if (tasks.size() == 1) {
            System.out.println("Now you have " + tasks.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }
}
