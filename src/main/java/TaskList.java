import java.util.ArrayList;


public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Obtains the number of tasks in the TaskList
     */
    public Integer size() {
        return tasks.size();
    }

    /**
     * Obtains the specific task in the TaskList
     */
    public Task get(Integer i) {
        return tasks.get(i);
    }

    /**
     * Delete the specific task in the TaskList
     */
    public void remove(int taskIndex) {
        tasks.remove(taskIndex);
    }

    /**
     Obtains all the tasks in the TaskList
     */
    public ArrayList<Task> getArr() {
        return tasks;
    }

    /**
     * Obtains the tasks that contains the keyword that input by the user and stores them in another task list.
     * Prints the new list obtained before
     * Prints the warning message if there is no matching task in the new task list
     *
     * @param keywordToFind the keyword that the user want all the matching tasks to contain
     */
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
