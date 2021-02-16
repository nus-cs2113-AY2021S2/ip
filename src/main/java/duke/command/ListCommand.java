package duke.command;

import duke.task.TaskManager;

public class ListCommand implements Command {

    public ListCommand(String input) {
    }

    public void execute(String input) {

        if (TaskManager.numOfTasks == 0) {
            System.out.println("You have no task! :)");
        }
        else {
            int count = 0;
            while (count < TaskManager.numOfTasks) {
                System.out.println(count + 1 + ": " + TaskManager.tasks.get(count));
                count++;
            }
        }

        end();
    }

    public static void end() {
        System.out.println("____________________________________________________________" + System.lineSeparator());
    }
}
