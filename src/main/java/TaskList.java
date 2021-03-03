import java.util.ArrayList;



public class TaskList {
        private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }



    public Task getTaskAtIndex(int taskIndex) {
        return tasks.get(taskIndex);
    }



}
