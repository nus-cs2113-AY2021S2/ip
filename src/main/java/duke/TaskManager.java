package duke;


import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;


public class TaskManager {
    private ArrayList<Task> tasks;

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

    // add
    public Todo addTodo(String content) {
        Task taskAdded = new Todo(content);
        tasks.add(taskAdded);
        return (Todo) taskAdded;
    }

    public Deadline addDeadline(String content, String by) {
        Task taskAdded = new Deadline(content, by);
        tasks.add(taskAdded);
        return (Deadline) taskAdded;
    }

    public Event addEvent(String content, String at) {
        Task taskAdded = new Event(content, at);
        tasks.add(taskAdded);
        return (Event) taskAdded;
    }
    // done
    public void markTaskDone(int taskIndexShow) {
        tasks.get(taskIndexShow - 1).setDone(true);
        System.out.println("Nice! I've marked this task as done:\n" + tasks.get(taskIndexShow - 1));
    }

    // list
    public void listAllTasks() {
        System.out.println("____________________________________________________________");
        for(int i=0; i< tasks.size() ; i++) {
            System.out.println(i+1 + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    //delete
    public void deleteTask(int taskIndexShow){
        Task temp = tasks.get(taskIndexShow - 1);
        tasks.remove(taskIndexShow - 1);
        System.out.println("Noted. I've removed this task: \n" + temp + "\nNow you have " + getNumOfTasks() + " tasks in the list.");
    }

}
