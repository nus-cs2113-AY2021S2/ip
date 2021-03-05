import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() { }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    static void printTaskList() {
        for(Task t: tasks){
            System.out.print(tasks.indexOf(t)+1+". ");
            System.out.println(t);
        }
    }

    public Task getTaskByIndex(int i) {
        return tasks.get(i);
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void removeTask(int i) {
        tasks.remove(i);
    }
}
