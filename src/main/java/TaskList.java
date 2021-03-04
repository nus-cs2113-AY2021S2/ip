import java.util.ArrayList;


public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Integer size() {
        return tasks.size();
    }

    public Task get(Integer i) {
        return tasks.get(i);
    }

    public void remove(int taskIndex) {
        tasks.remove(taskIndex);
    }

    public ArrayList<Task> getArr() {
        return tasks;
    }




}
