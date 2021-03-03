package commands;

import task.Deadline;

public class AddDeadlineFromFile extends Command{

    /**
     * add deadline from save file
     * @param commandDone whether the deadline is done or not
     * @param commandArgs description of the deadline
     */
    public static void execute(String commandDone, String commandArgs) {
        final String[] descriptionAndDeadline = parser.splitDescriptionAndDeadline(commandArgs);
        Deadline deadline = new Deadline(descriptionAndDeadline[0], descriptionAndDeadline[1]);
        if (commandDone.equals("1")) {
            deadline.markAsDone();
        }
        taskManager.addTask(deadline);
    }
}
