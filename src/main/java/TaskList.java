
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(Task inputTask) {
        tasks.add(inputTask);
        Output.printAdded(inputTask, tasks.size());
    }

    public void updateCompletion(int taskIndex) {
        tasks.get(taskIndex - 1).setDone();
        Output.printUpdated(tasks.get(taskIndex - 1).toString());
    }

    public void printTaskList() {
        Output.printTaskList(tasks);
    }

    public int size() {
        return tasks.size();
    }

}