import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void printTasks() {
        int taskId = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task: tasks) {
            System.out.println(taskId + ". " + task.toString());
            taskId++;
        }
    }

    public void markDone(int taskIdNum) {
        if (taskIdNum > 0 && taskIdNum <= tasks.size()) {
            int taskListIndexNum = taskIdNum-1;
            Task task = tasks.get(taskListIndexNum);
            task.setAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.toString());
        } else {
            System.out.println("Invalid task number.");
            printTasksNumber();
        }
    }

    public void printTasksNumber() {
        if (tasks.size() == 1) {
            System.out.println("Now you have " + tasks.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    public void addTodoToList(String description) {
        Todo todoTask = new Todo(description);
        addTaskToList(todoTask);
    }

    public void addEventToList(String description, String at) {
        Event eventTask = new Event(description, at);
        addTaskToList(eventTask);
    }

    public void addDeadlineToList(String description, String by){
        Deadline deadlineTask = new Deadline(description, by);
        addTaskToList(deadlineTask);
    }

    private void addTaskToList(Task task){
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        printTasksNumber();
    }
}
