package duke.command;

import duke.taslist.TaskList;
import duke.ui.Ui;

/**
 * Lists all the tasks in the taskList
 */
public class ListCommand implements Command {

    public ListCommand(String input) {
    }

    /**
     * List all the tasks in the taskList
     *
     * @param input
     */
    public void execute(String input) {

        if (TaskList.numOfTasks == 0) {
            System.out.println("You have no task! :)");
        }
        else {
            int count = 0;
            while (count < TaskList.numOfTasks) {
                System.out.println(count + 1 + ": " + TaskList.getTask(count));
                count++;
            }
        }
        Ui.commandDone();
    }
}
