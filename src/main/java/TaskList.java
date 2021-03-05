import java.util.ArrayList;

/**
 * This class contains the task list and has operation to manipulate the list
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Consturctor method to create a TaskList object with an existing list of tasks
     * @param tasks an existing list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor method to create an empty task list.
     */
    public TaskList() { }

    /**
     * Get the entire list of tasks.
     * @return the entire list of tasks
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Print the tasks on the list one by one.
     */
    static void printTaskList() {
        for(Task t: tasks){
            System.out.print(tasks.indexOf(t)+1+". ");
            System.out.println(t);
        }
    }

    /**
     * Get a specific task on the list using its index
     * @param i index of the task
     * @return the task at this index
     */
    public Task getTaskByIndex(int i) {
        return tasks.get(i);
    }

    /**
     * Add a task to the existing list of task
     * @param t the new task to add
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Remove a specific task on the list using its index
     * @param i the index of the task to remove
     */
    public void removeTask(int i) {
        tasks.remove(i);
    }
}
