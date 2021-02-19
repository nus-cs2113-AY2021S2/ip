package commands;

import tasks.Task;

public class FindCommand extends Command {

    private final String input;

    public FindCommand(String input) {
        this.input = input.substring(constants.COMMAND_FIND.length());
    }


    @Override
    public void execute() {
        System.out.println(constants.LINE);
        int count = 0;
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            Task task = taskManager.getTask(i);
            if (task.getName().toLowerCase().contains(input.toLowerCase())) {
                count++;
                System.out.print(count + ".");
                task.printStatus();
                System.out.println();
            }
        }
        if (count == 0) {
            System.out.println(constants.MESSAGE_TASK_NOT_FOUND);
        }
        System.out.println(constants.LINE);
    }
}
