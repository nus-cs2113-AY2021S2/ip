package duke.command;

import duke.ui.Menu;
import duke.task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand() {
        super(CommandType.LIST);
    }


    @Override
    public void execute(ArrayList<Task> tasks) {
        String outputText;
        outputText = "Here are the tasks in your list:";
        for (Task task : tasks) {
            String taskNumber = (tasks.indexOf(task) + 1) + ".";
            outputText += System.lineSeparator() +
                    " " +
                    taskNumber + " " +
                    task;
        }
        Menu.printText(outputText);
    }
}
