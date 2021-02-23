package commands;

import exceptions.NoResultException;
import task.Task;
import exceptions.EmptyDescriptionException;

public class Find extends Command{

    public static void execute(String commandArgs) throws EmptyDescriptionException, NoResultException {
        if (commandArgs.equals("")) {
            throw new EmptyDescriptionException();
        }
        System.out.println(messages.MESSAGE_SHOW_MATCHING_LIST);
        int taskCount = 1;
        for (Task task : taskManager.getTaskList()) {
            if (task.getDescription().contains(commandArgs)) {
                System.out.println(taskCount + "." + task.toString());
                ++taskCount;
            }
        }
        if (taskCount == 1) {
            throw new NoResultException();
        }
    }
}
