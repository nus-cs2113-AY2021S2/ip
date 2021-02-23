package commands;

import task.Task;

public class ShowTasksList extends Command{

    public static void execute() {
        System.out.println(messages.MESSAGE_SHOW_LIST);
        int taskCount = 1;
        for (Task task: taskManager.getTaskList()) {
            System.out.println(taskCount + "." + task.toString());
            ++taskCount;
        }
    }
}
