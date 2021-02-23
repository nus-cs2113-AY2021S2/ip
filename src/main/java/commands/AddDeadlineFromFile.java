package commands;

import task.Deadline;

public class AddDeadlineFromFile extends Command{

    public static void execute(String commandDone, String commandArgs) {
        final String[] descriptionAndDeadline = parser.splitDescriptionAndDeadline(commandArgs);
        Deadline deadline = new Deadline(descriptionAndDeadline[0], descriptionAndDeadline[1]);
        if (commandDone.equals("1")) {
            deadline.markAsDone();
        }
        taskManager.addTask(deadline);
    }
}
