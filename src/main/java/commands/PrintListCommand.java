package commands;

public class PrintListCommand extends Command {

    /**
     * Prints list of task name.
     */
    @Override
    public void execute() {
        System.out.println(constants.LINE);
        if (taskManager.getTaskCount() == 0) {
            System.out.println(constants.MESSAGE_EMPTY_LIST);
            return;
        }
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            System.out.print((i + 1) + ".");
            taskManager.getTask(i).printStatus();
            System.out.println();
        }
        System.out.println(constants.LINE);
    }

}
