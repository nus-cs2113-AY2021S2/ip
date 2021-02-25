package duke.command;

import com.sun.source.util.TaskListener;
import duke.TaskList;
import duke.Ui;


public class ListCommand implements Command {

    public ListCommand(String input) {
    }

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
