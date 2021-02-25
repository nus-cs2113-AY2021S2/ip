package duke.command;

import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {

    public FindCommand(String input){
    }

    public void execute(String input) {
        String[] keyword = input.split(" ", 2);
        if (TaskList.numOfTasks == 0) {
            System.out.println("You have no task! :)");
        }
        else {
            int count = 0;
            int i = 1;
            while (count < TaskList.numOfTasks) {
                if (TaskList.findTask(keyword[1], count)) {
                    System.out.println(i + ": " + TaskList.getTask(count));
                    i++;
                }
                count++;
            }
            if (i == 1) {
                System.out.println("There is no such task!");
            }
        }
        Ui.commandDone();
    }
}
