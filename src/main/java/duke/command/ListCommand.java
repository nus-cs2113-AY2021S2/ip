package duke.command;

import duke.ui.Menu;
import duke.task.Task;

public class ListCommand extends Command {

    public ListCommand() {
        super(CommandType.LIST);
    }


    @Override
    public void execute(Task[] tasks) {
        String outputText;
        outputText = "Here are the tasks in your list:";
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            String taskNumber = (i + 1) + ".";
            outputText += System.lineSeparator() +
                    " " +
                    taskNumber + " " +
                    tasks[i].toString();
        }
        Menu.printText(outputText);
    }
}
