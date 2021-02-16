package duke.task;

import duke.Duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void showAddResult(Task t) {
        Duke.showExecuteResult("Got it. I've added this task:\n" + t + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void addTodo(String content) {
        Task taskAdded = new Todo(content);
        tasks.add(taskAdded);
        showAddResult(taskAdded);
    }

    public void addDeadline(String content, String by) {
        Task taskAdded = new Deadline(content, by);
        tasks.add(taskAdded);
        showAddResult(taskAdded);
    }

    public void addEvent(String content, String at) {
        Task taskAdded = new Event(content, at);
        tasks.add(taskAdded);
        showAddResult(taskAdded);
    }

    public void markTaskDone(int taskIndexShow) {
        tasks.get(taskIndexShow - 1).setDone(true);
        Duke.showExecuteResult("Nice! I've marked this task as done:\n" + tasks.get(taskIndexShow - 1));
    }

    public void listAllTasks() {
        System.out.println("____________________________________________________________");
        for(int i=0; i< tasks.size() ; i++) {
            System.out.println(i+1 + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public int getNumOfTasks() {
        return tasks.size();
    }


}
