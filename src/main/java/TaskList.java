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


    public void getFoundTask(String keywordToFind, Ui ui) {
        ArrayList<Task> foundList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keywordToFind)) {
                foundList.add(task);
            }
        }
        if (foundList.size() != 0) {
            ui.printFoundList(foundList);
        } else {
            ui.printErrorMessage();
        }
    }


}
