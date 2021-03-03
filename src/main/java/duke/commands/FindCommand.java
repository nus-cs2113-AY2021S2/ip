package duke.commands;

import duke.TaskList;
import duke.task.Task;

public class FindCommand implements Command {
    String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks) {
        if (tasks.getTaskCount() == 0) {
            tasks.printNumTasks();
            return;
        }

        int i = 0;
        for (Task task : tasks.getTaskList()) {
            if (task.getDescription().contains(this.keyword)) {
                i++;
                if (i == 1) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println(i + "." + task.toString());
            }
        }
        if (i == 0) {
            System.out.println("'" + this.keyword + "' is not found in the description of any of your tasks!");
        }
    }
}
