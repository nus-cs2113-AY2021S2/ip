package commands;

import tasks.Task;

public class FindCommand extends Command {

    private final String input;

    public FindCommand(String input) {
        this.input = input.substring(constants.COMMAND_FIND.length());
    }


    /**
     * Prints all tasks that matches keyword.
     */
    @Override
    public void execute() {
        int count = 0;
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            Task task = taskManager.getTask(i);
            if (!task.getName().toLowerCase().contains(input.toLowerCase())) {
                continue;
            } else if (count == 0) {
                System.out.println(constants.LINE);
            }
            count++;
            System.out.print((i+1) + ".");
            task.printStatus();
            System.out.println();
        }
        if (count == 0) {
            //No matching task
            System.out.println(constants.MESSAGE_TASK_NOT_FOUND);
        } else {
            System.out.println(constants.LINE);
        }
    }
}
