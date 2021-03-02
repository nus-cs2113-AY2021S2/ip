package commands;

import io.DukePrint;
import models.Task;
import models.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String search;
    private TaskList taskList;

    public FindCommand(String search, TaskList taskList,
                       DukePrint dukePrint) {
        super(dukePrint);
        this.search = search;
        this.taskList = taskList;
    }

    private ArrayList<Task> findTasks() {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            if (task.getDescription().contains(search)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    @Override
    public void execute() {
        ArrayList<Task> matchingTasks = findTasks();
        dukePrint.printFind(matchingTasks);
    }
}
