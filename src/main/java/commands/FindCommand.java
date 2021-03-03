package commands;

import io.DukePrint;
import models.Task;
import models.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {

    /**
     * Keyword that the user searches
     */
    private final String search;
    private TaskList taskList;

    public FindCommand(String search, TaskList taskList,
                       DukePrint dukePrint) {
        super(dukePrint);
        this.search = search;
        this.taskList = taskList;
    }

    /**
     * Searches the TaskList for any matches with the keyword
     *
     * @return matchingTasks ArrayList of Tasks that matches the keyword
     */
    private ArrayList<Task> findTasks() {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            if (task.getDescription().contains(search)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Prints the list of tasks that contain the relevant keyword
     */
    @Override
    public void execute() {
        ArrayList<Task> matchingTasks = findTasks();
        dukePrint.printFind(matchingTasks);
    }
}
