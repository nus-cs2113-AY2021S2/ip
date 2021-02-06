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
        } catch (IndexOutOfBoundsException error) {
            System.out.println("Error -> Cannot find task with the specified task number " + taskIdNum + ".");
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

    public void addDeadlineToList(String description, String by) {
        Deadline deadlineTask = new Deadline(description, by);
        addTaskToList(deadlineTask);
    }

    private void addTaskToList(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        printNumberOfTasks();
    }

    public void printNumberOfTasks() {
        if (tasks.size() == 1) {
            System.out.println("Now you have " + tasks.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }
}
